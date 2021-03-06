package com.davidkpv.kpvgit.utils;

import androidx.lifecycle.LiveData;

import com.davidkpv.kpvgit.api.ApiResponse;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class LiveDataCallAdapterFactory extends CallAdapter.Factory {

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if(getRawType(returnType) != LiveData.class){
            return null;
        }

        Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
        Class<?> rawObservableType = getRawType(observableType);

        if(rawObservableType != ApiResponse.class){
            throw new IllegalArgumentException("El tipo debe de ser un recurso");
        }
        if(!(observableType instanceof ParameterizedType)){
            throw new IllegalArgumentException("El recurso debe de ser parametrizado");
        }
        Type bodyType = getParameterUpperBound(0, (ParameterizedType) observableType);
        return new LiveDataCallAdapter<>(bodyType);
    }

}
