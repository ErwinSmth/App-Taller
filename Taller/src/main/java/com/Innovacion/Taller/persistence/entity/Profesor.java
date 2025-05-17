package com.Innovacion.Taller.persistence.entity;


import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name="profesor")

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

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
    private List<Taller> talleres;
    //Con cascade All en dependiendo de que clase decimos que por ejemplo
    //si se elimina un profesor tambien se elimine sus talleres


    public Long getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Long profesorId) {
        this.profesorId = profesorId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(String especialidades) {
        this.especialidades = especialidades;
    }

    public List<Taller> getTalleres() {
        return talleres;
    }

    public void setTalleres(List<Taller> talleres) {
        this.talleres = talleres;
    }
}
