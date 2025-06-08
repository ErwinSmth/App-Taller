package com.Innovacion.Taller.persistence.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "taller")
public class Taller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taller_id")
    private Long tallerId;

    @Column(name = "categoria_id")
    private Long idCategoria;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false, updatable = false, insertable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "organizador_id")
    private Organizador organizador;

    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;

    @Column(name = "titulo", nullable = false, length = 100, unique = true)
    private String titulo;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "duracion_horas", nullable = false)
    private Integer duracionHoras;

    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private LocalDate fechaRegistro;

    @Column(name = "imagen_url")
    private String imagenUrl;


    public Long getTallerId() {
        return tallerId;
    }

    public void setTallerId(Long tallerId) {
        this.tallerId = tallerId;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Organizador getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
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
}
