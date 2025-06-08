package com.Innovacion.Taller.domain.dto;

<<<<<<< HEAD:Taller/src/main/java/com/Innovacion/Taller/domain/dto/OrganizadorDto.java


public class OrganizadorDto {

    private Long organizadorId;
=======
public class OrganizadorDto {

    private Long organizadorId;
    private UsuarioDto userDto;
>>>>>>> main:Backend/Taller/src/main/java/com/Innovacion/Taller/domain/dto/OrganizadorDto.java
    private String razonSocial;
    private String ruc;
    private String descripcion;
    private String direccionSede;
<<<<<<< HEAD:Taller/src/main/java/com/Innovacion/Taller/domain/dto/OrganizadorDto.java
    private UsuarioDto usuarioDto;
    //falta lista talleres
=======

>>>>>>> main:Backend/Taller/src/main/java/com/Innovacion/Taller/domain/dto/OrganizadorDto.java
    public Long getOrganizadorId() {
        return organizadorId;
    }

    public void setOrganizadorId(Long organizadorId) {
        this.organizadorId = organizadorId;
    }

<<<<<<< HEAD:Taller/src/main/java/com/Innovacion/Taller/domain/dto/OrganizadorDto.java
=======
    public UsuarioDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UsuarioDto userDto) {
        this.userDto = userDto;
    }

>>>>>>> main:Backend/Taller/src/main/java/com/Innovacion/Taller/domain/dto/OrganizadorDto.java
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
<<<<<<< HEAD:Taller/src/main/java/com/Innovacion/Taller/domain/dto/OrganizadorDto.java

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }
=======
>>>>>>> main:Backend/Taller/src/main/java/com/Innovacion/Taller/domain/dto/OrganizadorDto.java
}
