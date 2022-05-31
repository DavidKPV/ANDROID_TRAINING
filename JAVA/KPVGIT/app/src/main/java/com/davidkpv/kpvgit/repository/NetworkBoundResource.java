package com.davidkpv.kpvgit.repository;

import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.davidkpv.kpvgit.AppExecutors;
import com.davidkpv.kpvgit.api.ApiResponse;

import java.util.Objects;

// CLASE QUE HARÁ PARTE DEL TRABAJO PARA SABER SI DEBE DE OBTENER LOS DATOS DE LA BD LOCAL
// O DE LO CONTRARIO REALIZAR LA CONSULTA AL WEB SERVICE
public abstract class NetworkBoundResource<ResultType, RequestType> {
    // NOS PERMITIRÁ INDICAR EN QUÉ HILO QUEREMOS QUE SE EJECUTE CADA COSA
    private final AppExecutors appExecutors;
    // SIRVE PARA OBTENER TODOS LOS LIVEDATA QUE SE MANEJEN Y LOS OBSERVA PARA NOTIFICAR SI ALGO CAMBIA
    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    // INDICAMOS CON LA SIGUIENTE ETIQUETA QUE SOLO SE DEBE EJECUTAR EN EL HILO PRINCIPAL
    @MainThread
    NetworkBoundResource(AppExecutors appExecutors){
        this.appExecutors = appExecutors;
        result.setValue(Resource.loading(null));
        // VER SI TENEMOS DATOS EN LA BD LOCAL
        LiveData<ResultType> dbSource = loadFromDb();
        // OBSERVAR SI HAY ALGÚN CAMBIO EN LA BASE DE DATOS
        result.addSource(dbSource, new Observer<ResultType>() {
            @Override
            public void onChanged(ResultType data) {
                result.removeSource(dbSource);
                // VERIFICAMOS SI LOS DATOS ESTÁN BIEN DENTRO DE LA BD O SI NO HAN EXPIRADO
                if(NetworkBoundResource.this.shouldFetch(data)){
                    NetworkBoundResource.this.fetchFromNetwork(dbSource);
                } else {
                    result.addSource(dbSource, (ResultType newData)->{
                        NetworkBoundResource.this.setValue(Resource.success(newData));
                    });
                }
            }
        });
    }

    @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    @MainThread
    protected abstract boolean shouldFetch(ResultType data);

    // MÉTODO PARA OBTENER NUESTROS DATOS DESDE EL WEB SERVICE
    private void fetchFromNetwork(final LiveData<ResultType> dbSource){
        LiveData<ApiResponse<RequestType>> apiResponse = createCall();
        // MOSTRAREMOS LOS DATOS AL WEB SERVICE PARA SABER SI RESPONDE, PERO EN CASSO CONTRARIO
        // MOSTRAR LOS DE LA BD LOCAL
        result.addSource(dbSource, new Observer<ResultType>() {
            @Override
            public void onChanged(ResultType newData) {
                NetworkBoundResource.this.setValue(Resource.loading(newData));
            }
        });
        result.addSource(apiResponse, response -> {
            result.removeSource(apiResponse);
            result.removeSource(dbSource);
            // VERIFICAMOS SI LA PETICIÓN WEB FUE CORRECTA
            if(response.isSuccessful()){
                // GUARDAMOS LOS DATOS EN LA MEMORIA LOCAL (ROOM)
                appExecutors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        NetworkBoundResource.this.saveCallResult(NetworkBoundResource.this.processResponse(response));
                        appExecutors.mainThread().execute(()-> {
                            result.addSource(NetworkBoundResource.this.loadFromDb(), newData ->
                                    NetworkBoundResource.this.setValue(Resource.success(newData)));
                        });
                    }
                });
            } else {
                // EN CASO DE QUE LA LLAMADA AL WEB SERVICE HAYA FALLADO
                onFetchFailed();
                result.addSource(dbSource, newData ->
                        setValue(Resource.error(response.errorMessage, newData)));
            }
        });
    }

    // COMPARAMOS SI LOS DATOS ANTIGUOS CON LOS NUEVOS SON LOS MISMOS
    @MainThread
    private void setValue(Resource<ResultType> newValue){
        if(!Objects.equals(result.getValue(), newValue)){
            result.setValue(newValue);
        }
    }

    protected void onFetchFailed(){

    }

    public LiveData<Resource<ResultType>> asLiveData(){
        return result;
    }

    // EN LA SIGUIENTE ETIQUETA INDICAMOS QUE QUEREMOS QUE SE EJECUTEN EN UN HILO SECUNDARIO
    @WorkerThread
    protected RequestType processResponse(ApiResponse<RequestType> response){
        return response.body;
    }

    @WorkerThread
    protected abstract void saveCallResult(RequestType item);

    @MainThread
    protected abstract LiveData<ApiResponse<RequestType>> createCall();

}
