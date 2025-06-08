package com.Innovacion.Taller.persistence.mapper;

import com.Innovacion.Taller.domain.dto.OrganizadorDto;
import com.Innovacion.Taller.persistence.entity.Organizador;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring",uses = {UsuarioMapper.class})
public interface OrganizadorMapper {

    @Mapping(source = "usuario", target = "userDto")
    OrganizadorDto toOrganizadorDto(Organizador organizador);

    @InheritInverseConfiguration
    @Mapping(source = "userDto", target = "usuario")
    Organizador toOrganizador(OrganizadorDto organizadorDto);
}
