package com.example.tallerandroid.net.apis;

import com.example.tallerandroid.model.Especialidad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEspecialidadService {

    @GET("/especialidad/listar")
    Call<List<Especialidad>> listarEspecialidades();

}
