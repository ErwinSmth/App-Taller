package com.Innovacion.Taller.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolesDto {
    private Long rolId;
    private String rolName;
    private String descripcion;
}
