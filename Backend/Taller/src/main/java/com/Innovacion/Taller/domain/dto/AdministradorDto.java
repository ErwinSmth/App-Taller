package com.Innovacion.Taller.domain.dto;

public class AdministradorDto {

    private Long administradorId;
    private UsuarioDto userDto;

    public Long getAdministradorId() {
        return administradorId;
    }

    public void setAdministradorId(Long administradorId) {
        this.administradorId = administradorId;
    }

    public UsuarioDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UsuarioDto userDto) {
        this.userDto = userDto;
    }
}
