package com.Innovacion.Taller.persistence.entity;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name = "categoria")
@Data
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Long categoriaId;

    @Column (name = "nombre",nullable = false,length = 50, unique = true)
    private String nombre;

    @Column (name="descripcion")
    private String descripcion;
}
