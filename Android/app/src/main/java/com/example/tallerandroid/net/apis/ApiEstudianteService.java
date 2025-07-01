package com.example.tallerandroid.net.apis;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiEstudianteService {

    @GET("/AppTaller/estudiante/usuario/{userId}")
    Call<JsonObject> obtenerPorUsuario(@Path("userId") long userId);

}
