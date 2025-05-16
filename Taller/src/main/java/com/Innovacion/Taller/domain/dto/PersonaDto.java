package com.Innovacion.Taller.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDto {

//Datos de la persona
    private Long personaId;
    private String nombres;
    private String apellidos;
    private String DNI;
    private String telefono;
    private String email;
    private LocalDate fechaNacimiento;
    private LocalDateTime fechaRegistro;
}
