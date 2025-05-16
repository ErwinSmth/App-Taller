package com.Innovacion.Taller.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    private Long userId;
    private String nameUser;
    private String contraseña;
    private LocalDate fechaRegistro;
    private boolean activo;
    private PersonaDto personDto;
    private List<RolesDto> roles;

}
