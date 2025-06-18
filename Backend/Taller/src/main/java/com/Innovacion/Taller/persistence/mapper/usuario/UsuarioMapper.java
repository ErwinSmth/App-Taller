package com.Innovacion.Taller.persistence.mapper.usuario;

import com.Innovacion.Taller.domain.dto.usuario.UsuarioDto;
import com.Innovacion.Taller.persistence.entity.usuario.Usuario;
import com.Innovacion.Taller.persistence.mapper.persona.PersonaMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PersonaMapper.class, RolMapper.class})
public interface UsuarioMapper {

    @Mapping(source = "persona", target = "personDto")
    @Mapping(source = "roles", target = "roles")
    @Mapping(target = "permisos", ignore = true)
    UsuarioDto toUsuarioDto(Usuario usuario);

    List<UsuarioDto> toUsuarioDtoList(List<Usuario> usuarios);

    @InheritInverseConfiguration
    @Mapping(source = "personDto", target = "persona")
    @Mapping(source = "roles", target = "roles")
    Usuario toUsuario(UsuarioDto usuarioDto);

    List<Usuario> toUsuarioList(List<UsuarioDto> usuarioDtos);

}
