package com.davidkpv.kpvgit.api;

import android.app.Application;
import android.util.ArrayMap;
import android.util.Log;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Response;

// DE ESTA MANERA INDICAMOS QUE SERÁ UNA API A LA QUE SE LE PODRÁN INGRESAR DIFERENTES TIPOS DE CLASES
// ESTA CLASE ES UTILIZADA PARA MANEJAR TODAS LAS RESPUESTAS DE LA API
public class ApiResponse<T> {
    // COLOCAMOS UN PAR DE EXPRESIONES REGULARES PARA LA BÚSQUEDA O MEJOR CONOCIDOS COMO REGEX
    private static final Pattern LINK_PATTERN = Pattern
            .compile("<([^>]*)>[\\s]*rel=\"([a-zA-Z0-9]+)\"");
    private static final Pattern PAGE_PATTERN = Pattern.compile("\\bpage=(\\d+)");

    private static final String NEXT_LINK = "next";
    public final int code;
    public final T body;
    public final String errorMessage;
    public final Map<String, String> links;

    // EN CASO DE QUE LA LLAMADA A LA API VUELVA UN ERROR
    public ApiResponse(Throwable error){
        code = 500;
        body = null;
        errorMessage = error.getMessage();
        links = Collections.emptyMap();
    }

    // EN CASO DE QUE LA LLAMADA DE LA API SEA EXITOSA
    public ApiResponse(Response<T> response){
        code = response.code();
        if(response.isSuccessful()){
            body = response.body();
            errorMessage = null;
        } else {
            String message = null;
            if(response.errorBody() != null){
                try{
                    message = response.errorBody().string();
                } catch (IOException ignored){
                    Log.d(ignored.getMessage(), "Error mientras parsea respuesta");
                }
            }
            if(message == null || message.trim().length() == 0){
                message = response.message();
            }
            errorMessage = message;
            body = null;
        }
        String linkHeader = response.headers().get("links");
        if(linkHeader == null){
            links = Collections.emptyMap();
        } else {
            links = new ArrayMap<>();
            Matcher matcher = LINK_PATTERN.matcher(linkHeader);

            while (matcher.find()){
                int count = matcher.groupCount();
                if(count == 2){
                    links.put(matcher.group(2), matcher.group(1));
                }
            }
        }
    }

    public Integer getNextPage(){
        String next = links.get(NEXT_LINK);
        if(next == null){
            return null;
        }
        Matcher matcher = PAGE_PATTERN.matcher(next);

        if(!matcher.find() || matcher.groupCount() != 1){
            return null;
        }
        try{
            return Integer.parseInt(matcher.group(1));
        } catch (NumberFormatException ex){
            Log.d("Cannot parse next", next);
            return null;
        }
    }

    // ANALIZAR SI LA RESPUESTA FUE SATISFACTORIA
    public boolean isSuccessful(){
        return code >= 200 && code < 300;
    }
}
