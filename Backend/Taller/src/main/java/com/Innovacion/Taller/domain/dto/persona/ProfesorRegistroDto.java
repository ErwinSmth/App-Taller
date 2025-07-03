package com.Innovacion.Taller.domain.dto.persona;

import java.util.List;

public class ProfesorRegistroDto {

    private Long userId;
    private String descripcion;
    private List<Long> especialidadesR;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Long> getEspecialidadesR() {
        return especialidadesR;
    }

    public void setEspecialidadesR(List<Long> especialidadesR) {
        this.especialidadesR = especialidadesR;
    }
}
