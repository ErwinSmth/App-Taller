package com.example.tallerandroid.net.apis;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiUserService {

    @POST("/AppTaller/usuario/login")
    Call<JsonObject> login(@Body JsonObject body);

    @POST("/AppTaller/usuario/registrar")
    Call<JsonObject> registrarUsuario(@Body JsonObject body);

    @PUT("/AppTaller/usuario/{id}")
    Call<JsonObject> editarUsuario(@Path("id") Long id, @Body JsonObject body);

}
