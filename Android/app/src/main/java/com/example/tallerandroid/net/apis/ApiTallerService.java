package com.example.tallerandroid.net.apis;

import com.example.tallerandroid.model.TallerCrearRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiTallerService {

    @POST("/AppTaller/taller/crear")
    Call<Void> crearTaller(@Body TallerCrearRequest request);

}
