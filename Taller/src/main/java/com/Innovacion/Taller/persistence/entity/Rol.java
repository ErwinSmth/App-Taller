package com.Innovacion.Taller.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;


import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rol")
@Data
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Long rolId;

    @Column(name = "rol_name", nullable = false, length = 50, unique = true)
    private String rolName;

    @Column(length = 200)
    private String descripcion;

    //Se usa set y no list para que los usuarios no puedan repetir roles
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<Usuario> usuarios = new HashSet<>();

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
