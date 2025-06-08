package com.Innovacion.Taller.domain.dto;

public class ProfesorDto {

    private Long profesorId;
<<<<<<< HEAD:Taller/src/main/java/com/Innovacion/Taller/domain/dto/ProfesorDto.java
    private String descripcion;
    private String especialidades;
    private UsuarioDto usuarioDto;
   //falta lista talleres

=======
    private UsuarioDto userDto;
    private String descripcion;
    private String especialidades;
>>>>>>> main:Backend/Taller/src/main/java/com/Innovacion/Taller/domain/dto/ProfesorDto.java

    public Long getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Long profesorId) {
        this.profesorId = profesorId;
    }

<<<<<<< HEAD:Taller/src/main/java/com/Innovacion/Taller/domain/dto/ProfesorDto.java
=======
    public UsuarioDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UsuarioDto userDto) {
        this.userDto = userDto;
    }

>>>>>>> main:Backend/Taller/src/main/java/com/Innovacion/Taller/domain/dto/ProfesorDto.java
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
<<<<<<< HEAD:Taller/src/main/java/com/Innovacion/Taller/domain/dto/ProfesorDto.java

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }
=======
>>>>>>> main:Backend/Taller/src/main/java/com/Innovacion/Taller/domain/dto/ProfesorDto.java
}
