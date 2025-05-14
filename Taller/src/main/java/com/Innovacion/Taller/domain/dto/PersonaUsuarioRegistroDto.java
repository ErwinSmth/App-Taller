package com.Innovacion.Taller.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaUsuarioRegistroDto {

//Datos de la persona
    private String nombres;
    private String apellidos;
    private String DNI;
    private String telefono;
    private String email;
    private LocalDate fechaNacimiento;


    //Datos usuario
    private String nameUser;
    private String contraseña;

    //Roles del usuario necesario porque al registrarse necesita regitrarse con su rol
    private List<Long> roles;

}
