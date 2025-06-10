package com.example.tallerandroid.net.apis;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiProfesorService {

    @POST("/AppTaller/profesor/especialidades")
    Call<Void> actualizarEspecialidadesyDescripcion(@Body JsonObject body);

    @GET("/AppTaller/profesor/usuario/{userId}")
    Call<JsonObject> obtenerUsuario(@Path("userId") Long userId);

    @POST("/AppTaller/profesor/crear")
    Call<JsonObject> crearProfesor(@Body JsonObject body);

}
