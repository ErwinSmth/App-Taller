package com.example.tallerandroid.model.request;

import com.example.tallerandroid.model.Categoria;

import java.util.List;

public class TallerCrearRequest {

    private String titulo;
    private String descripcion;
    private int duracionHoras;
    private double precio;
    private int capacidad;
    private Categoria categoria;
    private ProfesorRequest profesor;
    private List<TallerImagenRequest> imagenes;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(int duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ProfesorRequest getProfesor() {
        return profesor;
    }

    public void setProfesor(ProfesorRequest profesor) {
        this.profesor = profesor;
    }

    public List<TallerImagenRequest> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<TallerImagenRequest> imagenes) {
        this.imagenes = imagenes;
    }
}
