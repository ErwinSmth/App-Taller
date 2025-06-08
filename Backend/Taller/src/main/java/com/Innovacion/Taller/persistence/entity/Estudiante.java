package com.Innovacion.Taller.persistence.entity;

import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name="estudiante")
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

    public Long getEstudianteId(){return estudianteId;}

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getIntereses() {
        return intereses;
    }

    public void setIntereses(String intereses) {
        this.intereses = intereses;
    }
}
