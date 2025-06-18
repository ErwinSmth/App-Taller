package com.Innovacion.Taller.domain.dto.persona;


import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PersonaDto {

//Datos de la persona
    private Long personaId;

    @NotBlank(message = "Nombres requeridos")
    @Size(max = 100, message = "Maximo 100 caracteres")
    private String nombres;
    @NotBlank(message = "apellidos requeridos")
    @Size(max = 100, message = "Maximo 100 caracteres")
    private String apellidos;
    @NotNull(message = "DNI requerido")
    @Size(min = 8, max = 8, message = "DNI debe tener exactamente 8 caracteres")
    @Pattern(regexp = "\\d{8}", message = "DNI debe contener solo dígitos")
    private String dni;
    @NotNull(message = "Teléfono requerido")
    @Pattern(regexp = "\\d{7,15}", message = "Teléfono debe tener entre 7 y 15 dígitos")
    private String telefono;
    @NotBlank(message = "Email requerido")
    @Email(message = "Formato de email inválido")
    private String email;
    @NotNull(message = "Fecha de nacimiento requerida")
    private LocalDate fechaNacimiento;
    private LocalDateTime fechaRegistro;

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
