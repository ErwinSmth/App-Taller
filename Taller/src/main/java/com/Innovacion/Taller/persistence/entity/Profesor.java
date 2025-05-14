package com.Innovacion.Taller.persistence.entity;


import jakarta.persistence.*;

import lombok.Data;

import java.util.List;

@Entity
@Table(name="profesor")
@Data
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "profesor_id")
    private Long profesorId;

    //relacion de la llave foranea
    @OneToOne
    @JoinColumn(name="user_id",nullable = false, unique = true )
    private Usuario usuario;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="especialidades")
    private String especialidades;

    @OneToMany(mappedBy = "profesorId", cascade = CascadeType.ALL)
    private List<Taller> talleres;
    //Con cascade All en dependiendo de que clase decimos que por ejemplo
    //si se elimina un profesor tambien se elimine sus talleres

}
