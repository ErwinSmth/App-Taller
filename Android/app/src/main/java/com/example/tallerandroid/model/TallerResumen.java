package com.example.tallerandroid.model;

import java.util.List;

public class TallerResumen {

    private Long tallerId;
    private String titulo;
    private int capacidad;
    private double precio;
    private Long categoriaId;
    private String categoriaNombre;
    private List<TallerImagen> imagenes;

    public Long getTallerId() {
        return tallerId;
    }

    public void setTallerId(Long tallerId) {
        this.tallerId = tallerId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public List<TallerImagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<TallerImagen> imagenes) {
        this.imagenes = imagenes;
    }
}
