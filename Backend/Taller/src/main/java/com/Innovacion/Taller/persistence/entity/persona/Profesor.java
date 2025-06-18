package com.Innovacion.Taller.persistence.entity.persona;


import com.Innovacion.Taller.persistence.entity.Especialidad;
import com.Innovacion.Taller.persistence.entity.taller.Taller;
import com.Innovacion.Taller.persistence.entity.usuario.Usuario;
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

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
    private List<Taller> talleres;
    //Con cascade All en dependiendo de que clase decimos que por ejemplo
    //si se elimina un profesor tambien se elimine sus talleres

    @ManyToMany
    @JoinTable(
            name = "profesorespecialidad",
            joinColumns = @JoinColumn(name = "profesor_id"),
            inverseJoinColumns = @JoinColumn(name = "especialidad_id")
    )
    private List<Especialidad> especialidades;

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

    public List<Taller> getTalleres() {
        return talleres;
    }

    public void setTalleres(List<Taller> talleres) {
        this.talleres = talleres;
    }

    public List<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }
}
