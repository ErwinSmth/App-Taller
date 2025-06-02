package com.Innovacion.Taller.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TallerDto {

    private Long tallerId;
    private String titulo;
    private String descripcion;
    private Integer duracionHoras;
    private BigDecimal precio;
    private Integer capacidad;
    private LocalDate fechaRegistro;
    private String imagenUrl;
    private CategoriaDto categoria;
    private ProfesorDto profesor;
    private OrganizadorDto organizador;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(Integer duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public CategoriaDto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDto categoria) {
        this.categoria = categoria;
    }

    public ProfesorDto getProfesor() {
        return profesor;
    }

    public void setProfesor(ProfesorDto profesor) {
        this.profesor = profesor;
    }

    public OrganizadorDto getOrganizador() {
        return organizador;
    }

    public void setOrganizador(OrganizadorDto organizador) {
        this.organizador = organizador;
    }
}
