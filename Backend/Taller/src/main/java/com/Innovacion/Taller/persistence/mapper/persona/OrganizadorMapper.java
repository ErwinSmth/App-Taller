package com.Innovacion.Taller.persistence.mapper.persona;

import com.Innovacion.Taller.domain.dto.persona.OrganizadorDto;
import com.Innovacion.Taller.persistence.entity.persona.Organizador;
import com.Innovacion.Taller.persistence.mapper.usuario.UsuarioMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {UsuarioMapper.class})
public interface OrganizadorMapper {

    @Mapping(source = "usuario", target = "userDto")
    OrganizadorDto toOrganizadorDto(Organizador organizador);

    @InheritInverseConfiguration
    @Mapping(source = "userDto", target = "usuario")
    Organizador toOrganizador(OrganizadorDto organizadorDto);
}
