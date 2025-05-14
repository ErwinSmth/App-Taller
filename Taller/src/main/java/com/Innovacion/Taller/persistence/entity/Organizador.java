package com.Innovacion.Taller.persistence.entity;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name="organizador")
@Data

public class Organizador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "organizador_id")
    private Long organizadorId;

    //relacion de la llave foranea
    @OneToOne
    @JoinColumn(name="user_id",nullable = false, unique = true )
    private Usuario usuario;

    @Column(name="razon_social",nullable = false,length = 100,unique = true  )
    private String razonSocial;

    @Column(name="ruc",length = 11,unique = true)
    private String ruc;

    @Column (name = "descripcion")
    private String descripcion;

    @Column (name = "direccion_sede")
    private String direccionSede;

}
