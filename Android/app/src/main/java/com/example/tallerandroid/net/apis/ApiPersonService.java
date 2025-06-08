package com.example.tallerandroid.net.apis;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiPersonService {

    @POST("/AppTaller/personas/registrar")
    Call<JsonObject> registro(@Body JsonObject body);

}
