package com.Innovacion.Taller.domain.dto;

public class OrganizadorDto {

    private Long organizadorId;
    private UsuarioDto userDto;
    private String razonSocial;
    private String ruc;
    private String descripcion;
    private String direccionSede;

    public Long getOrganizadorId() {
        return organizadorId;
    }

    public void setOrganizadorId(Long organizadorId) {
        this.organizadorId = organizadorId;
    }

    public UsuarioDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UsuarioDto userDto) {
        this.userDto = userDto;
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
}
