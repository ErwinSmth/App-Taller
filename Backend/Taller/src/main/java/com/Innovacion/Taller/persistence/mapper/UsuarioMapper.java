package com.Innovacion.Taller.persistence.mapper;

import com.Innovacion.Taller.domain.dto.UsuarioDto;
import com.Innovacion.Taller.persistence.entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonaMapper.class, RolMapper.class})
public interface UsuarioMapper {

    @Mapping(source = "persona", target = "personDto")
    @Mapping(source = "roles", target = "roles")
    UsuarioDto toUsuarioDto(Usuario usuario);

    List<UsuarioDto> toUsuarioDtoList(List<Usuario> usuarios);

    @InheritInverseConfiguration
    @Mapping(source = "personDto", target = "persona")
    @Mapping(source = "roles", target = "roles")
    Usuario toUsuario(UsuarioDto usuarioDto);

    List<Usuario> toUsuarioList(List<UsuarioDto> usuarioDtos);

}
