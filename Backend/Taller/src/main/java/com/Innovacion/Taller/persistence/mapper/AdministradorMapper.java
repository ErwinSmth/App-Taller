package com.Innovacion.Taller.persistence.mapper;

import com.Innovacion.Taller.domain.dto.AdministradorDto;
import com.Innovacion.Taller.persistence.entity.Administrador;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UsuarioMapper.class)
public interface AdministradorMapper {

    @Mapping(source = "usuario", target = "userDto")
    AdministradorDto toAdministradorDto(Administrador admin);

    @InheritInverseConfiguration
    @Mapping(source = "userDto", target = "usuario")
    Administrador toAdministrador(AdministradorDto adminDto);

}
