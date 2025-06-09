package com.example.tallerandroid.net.apis;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiUserService {

    @POST("/AppTaller/usuario/login")
    Call<JsonObject> login(@Body JsonObject body);

    @POST("/AppTaller/usuario/registrar")
    Call<JsonObject> registrarUsuario(@Body JsonObject body);

}
