package com.Innovacion.Taller.domain.dto.persona;

import java.util.List;

public class ProfesorEspecialidadRequestDto {

    private Long profesorId;
    private String descripcion;
    private List<Long> especialidades;

    // Getters y setters
    public Long getProfesorId() { return profesorId; }
    public void setProfesorId(Long profesorId) { this.profesorId = profesorId; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<Long> getEspecialidades() { return especialidades; }
    public void setEspecialidades(List<Long> especialidades) { this.especialidades = especialidades; }

}
