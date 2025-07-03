package com.Innovacion.Taller.domain.dto.taller;

import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

//Clase pensada a ser usada en una vista donde se muestren todos los talleres
public class TallerResumenDto {

    private Long tallerId;
    private String titulo;
    private Integer capacidad;
    private BigDecimal precio;
    private Long categoriaId;
    private String categoriaNombre;
    private List<TallerImagenDto> imagenes;
    private LocalDate fechaFinalizacion;

    //Datos para mostrar en front nada mas del profesor y organizador
    private Long profesorId;
    private String profesorNombre;
    private Long organizadorId;
    private String organizadorNombre;

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

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public Long getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Long profesorId) {
        this.profesorId = profesorId;
    }

    public String getProfesorNombre() {
        return profesorNombre;
    }

    public void setProfesorNombre(String profesorNombre) {
        this.profesorNombre = profesorNombre;
    }

    public Long getOrganizadorId() {
        return organizadorId;
    }

    public void setOrganizadorId(Long organizadorId) {
        this.organizadorId = organizadorId;
    }

    public String getOrganizadorNombre() {
        return organizadorNombre;
    }

    public void setOrganizadorNombre(String organizadorNombre) {
        this.organizadorNombre = organizadorNombre;
    }

    public List<TallerImagenDto> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<TallerImagenDto> imagenes) {
        this.imagenes = imagenes;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }
}
