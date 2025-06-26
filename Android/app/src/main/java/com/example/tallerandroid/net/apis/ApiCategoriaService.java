package com.example.tallerandroid.net.apis;

import com.example.tallerandroid.model.Categoria;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCategoriaService {

    @GET("/AppTaller/categoria/listar")
    Call<List<Categoria>> listarCategorias();

}
