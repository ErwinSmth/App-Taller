package com.example.tallerandroid.net.apis;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiPersonService {

    @POST("/AppTaller/personas/registrar")
    Call<JsonObject> registro(@Body JsonObject body);

    @PUT("/AppTaller/personas/{id}")
    Call<JsonObject> editarPersona(@Path("id") long id, @Body JsonObject body);

}
