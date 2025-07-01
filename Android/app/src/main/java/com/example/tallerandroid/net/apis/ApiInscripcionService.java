package com.example.tallerandroid.net.apis;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInscripcionService {

    @POST("AppTaller/inscripcion/registrar")
    Call<Void> inscribirEstudiante(@Query("tallerId") long tallerId, @Query("estudianteId") long estudianteId);

}
