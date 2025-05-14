package com.Innovacion.Taller.persistence.entity;

import jakarta.persistence.*;

import lombok.Data;

import java.util.List;

@Entity
@Table(name="estudiante")
@Data
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "estudiante_id")
    private Long estudianteId;

    //relacion de la llave foranea
    @OneToOne
    @JoinColumn(name="user_id",nullable = false, unique = true )
    private Usuario usuario;

    @Column (name = "intereses")
    private String intereses;


}
