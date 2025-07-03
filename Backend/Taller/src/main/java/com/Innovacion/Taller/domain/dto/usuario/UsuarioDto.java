package com.Innovacion.Taller.domain.dto.usuario;


import com.Innovacion.Taller.domain.dto.persona.PersonaDto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioDto {

    private Long userId;
    private String nameUser;
    private String contraseña;
    private LocalDate fechaRegistro;
    private boolean activo;
    private PersonaDto personDto;
    private List<RolesDto> roles;
    private Map<Long, List<PermisoDto>>  permisos; // <- para ver los permisos de un usuario segun su rol

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public PersonaDto getPersonDto() {
        return personDto;
    }

    public void setPersonDto(PersonaDto personDto) {
        this.personDto = personDto;
    }

    public List<RolesDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesDto> roles) {
        this.roles = roles;
    }

    public Map<Long, List<PermisoDto>> getPermisos() {
        return permisos;
    }

    public void setPermisos(Map<Long, List<PermisoDto>> permisos) {
        this.permisos = permisos;
    }

}
