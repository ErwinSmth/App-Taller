package com.Innovacion.Taller.persistence.mapper.usuario;

import com.Innovacion.Taller.domain.dto.usuario.RolesDto;
import com.Innovacion.Taller.persistence.entity.usuario.Rol;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RolMapper {

    RolesDto toRolesDto(Rol rol);

    @InheritInverseConfiguration
    @Mapping(target = "usuarios", ignore = true)
    Rol toRol(RolesDto rolesDto);

}
