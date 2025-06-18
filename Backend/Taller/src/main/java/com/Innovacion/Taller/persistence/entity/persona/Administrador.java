package com.Innovacion.Taller.persistence.entity.persona;

import com.Innovacion.Taller.persistence.entity.usuario.Usuario;
import jakarta.persistence.*;

@Entity
@Table(name = "administrador")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "administrador_id")
    private Long administradorId;

    @OneToOne
    @JoinColumn(name="user_id",nullable = false, unique = true )
    private Usuario usuario;

    public Long getAdministradorId() {
        return administradorId;
    }

    public void setAdministradorId(Long administradorId) {
        this.administradorId = administradorId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
