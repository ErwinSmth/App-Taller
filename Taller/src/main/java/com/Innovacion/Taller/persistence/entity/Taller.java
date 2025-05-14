package com.Innovacion.Taller.persistence.entity;


import jakarta.persistence.*;

import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "taller")
@Data
public class Taller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taller_id")
    private Long tallerId;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoriaID;

    @ManyToOne
    @JoinColumn(name = "organizador_id")
    private Organizador organizadorId;

    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesorId;

    @Column(name = "titulo", nullable = false, length = 100, unique = true)
    private String titulo;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "duracion_horas", nullable = false)
    private Integer duracionHoras;

    @Column(name = "precio", nullable = false,precision = 10, scale = 2)
    private Float precio;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private LocalDate fechaRegistro;



}
