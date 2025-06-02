package com.Innovacion.Taller.domain.dto;

public class ProfesorDto {

    private Long profesorId;
    private UsuarioDto userDto;
    private String descripcion;
    private String especialidades;

    public Long getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Long profesorId) {
        this.profesorId = profesorId;
    }

    public UsuarioDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UsuarioDto userDto) {
        this.userDto = userDto;
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
}
