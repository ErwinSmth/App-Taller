package com.example.tallerandroid.net.apis;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiProfesorService {

    @POST("/profesor/especialidades")
    Call<Void> actualizarEspecialidadesyDescripcion(@Body JsonObject body);

    @GET("/profesor/usuario/{userId}")
    Call<Void> obtenerUsuario(@Path());

}
