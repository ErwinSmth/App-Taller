package com.example.tallerandroid.model;

import java.util.List;

public class Profesor {

    private Long profesorId;
    private Usuario userDto;
    private String descripcion;
    private List<Especialidad> especialidades;

    public Long getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Long profesorId) {
        this.profesorId = profesorId;
    }

    public Usuario getUserDto() {
        return userDto;
    }

    public void setUserDto(Usuario userDto) {
        this.userDto = userDto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }
}
