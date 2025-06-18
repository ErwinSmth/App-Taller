package com.Innovacion.Taller.persistence.mapper.usuario;

import com.Innovacion.Taller.domain.dto.usuario.PermisoDto;
import com.Innovacion.Taller.persistence.entity.usuario.Permiso;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermisoMapper {

    PermisoDto toPermisoDto(Permiso permiso);
    Permiso toPermiso(PermisoDto permisoDto);

}
