package com.Innovacion.Taller.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @JoinColumn(name = "persona_id", nullable = false, unique = true)
    private Persona persona;

    @Column(name = "name_user", nullable = false, length = 50, unique = true)
    private String nameUser;

    @Column(nullable = false)
    private String contraseña;

    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private LocalDate fechaRegistro;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean activo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuario_rol",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>();

    //Metodos para tener control sobre los roles
    public void addRol(Rol rol){
        if (rol != null){
            this.roles.add(rol);
            rol.getUsuarios().add(this);
        }
    }

    public void deteleRol(Rol rol){
        if (rol != null){
            this.roles.remove(rol);
            rol.getUsuarios().remove(this);
        }
    }
}
