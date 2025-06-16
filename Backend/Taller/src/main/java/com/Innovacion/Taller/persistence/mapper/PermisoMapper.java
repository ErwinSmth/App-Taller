package com.Innovacion.Taller.persistence.mapper;

import com.Innovacion.Taller.domain.dto.PermisoDto;
import com.Innovacion.Taller.persistence.entity.Permiso;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermisoMapper {

    PermisoDto toPermisoDto(Permiso permiso);
    Permiso toPermiso(PermisoDto permisoDto);

}
