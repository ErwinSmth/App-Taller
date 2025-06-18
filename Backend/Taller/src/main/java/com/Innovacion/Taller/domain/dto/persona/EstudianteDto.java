package com.Innovacion.Taller.domain.dto.persona;

import com.Innovacion.Taller.domain.dto.usuario.UsuarioDto;

public class EstudianteDto {
    private Long estudianteId;
    private String intereses;
    private UsuarioDto usuarioDto;

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public String getIntereses() {
        return intereses;
    }

    public void setIntereses(String intereses) {
        this.intereses = intereses;
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }
}
