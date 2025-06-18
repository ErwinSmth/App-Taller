package com.Innovacion.Taller.persistence.entity.persona;

import com.Innovacion.Taller.persistence.entity.taller.Taller;
import com.Innovacion.Taller.persistence.entity.usuario.Usuario;
import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name="organizador")

public class    Organizador {

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

    //Con cascade All en dependiendo de que clase decimos que por ejemplo
    //si se elimina un profesor tambien se elimine sus talleres
    @OneToMany(mappedBy = "organizador", cascade = CascadeType.ALL)
    private List<Taller> talleres;

    public Long getOrganizadorId() {
        return organizadorId;
    }

    public void setOrganizadorId(Long organizadorId) {
        this.organizadorId = organizadorId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccionSede() {
        return direccionSede;
    }

    public void setDireccionSede(String direccionSede) {
        this.direccionSede = direccionSede;
    }

    public List<Taller> getTalleres() {
        return talleres;
    }

    public void setTalleres(List<Taller> talleres) {
        this.talleres = talleres;
    }
}
