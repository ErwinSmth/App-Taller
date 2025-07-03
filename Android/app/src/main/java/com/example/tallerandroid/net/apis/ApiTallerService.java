package com.example.tallerandroid.net.apis;

import com.example.tallerandroid.model.TallerDetalle;
import com.example.tallerandroid.model.request.TallerCrearRequest;
import com.example.tallerandroid.model.TallerResumen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiTallerService {

    @POST("/AppTaller/taller/crear")
    Call<Void> crearTaller(@Body TallerCrearRequest request);

    @GET("/AppTaller/taller/profesor/{profesorId}")
    Call<List<TallerResumen>> listarTalleresPorProfesor(@Path("profesorId") long profesorId);

    // ApiTallerService.java
    @GET("/AppTaller/taller/categoria/{categoriaId}")
    Call<List<TallerResumen>> listarTallerPorCategoria(
            @Path("categoriaId") long categoriaId,
            @Query("userId") long userId
    );

    @GET("/AppTaller/taller/{id}")
    Call<TallerDetalle> getTallerById(@Path("id") long id);

    @PUT("/AppTaller/taller/{tallerId}/completar")
    Call<Void> completarTaller(@Path("tallerId") long tallerId);

}
