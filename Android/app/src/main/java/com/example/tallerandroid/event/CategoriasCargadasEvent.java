package com.example.tallerandroid.event;

import com.example.tallerandroid.model.Categoria;

import java.util.List;

public class CategoriasCargadasEvent {

    public final List<Categoria> categorias;


    public CategoriasCargadasEvent(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}
