package com.Innovacion.Taller.domain.dto;

import java.util.List;

public class ProfesorDto {

    private Long profesorId;
    private UsuarioDto userDto;
    private String descripcion;
    private List<EspecialidadDto> especialidades;
    //falta lista talleres


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

    public List<EspecialidadDto> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<EspecialidadDto> especialidades) {
        this.especialidades = especialidades;
    }
}
