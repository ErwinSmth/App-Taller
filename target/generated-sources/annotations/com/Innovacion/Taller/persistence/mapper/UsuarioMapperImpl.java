package com.Innovacion.Taller.persistence.mapper;

import com.Innovacion.Taller.domain.dto.RolesDto;
import com.Innovacion.Taller.domain.dto.UsuarioDto;
import com.Innovacion.Taller.persistence.entity.Rol;
import com.Innovacion.Taller.persistence.entity.Usuario;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-16T21:58:24-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Autowired
    private PersonaMapper personaMapper;
    @Autowired
    private RolMapper rolMapper;

    @Override
    public UsuarioDto toUsuarioDto(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setPersonDto( personaMapper.toPersonaDto( usuario.getPersona() ) );
        usuarioDto.setRoles( rolSetToRolesDtoList( usuario.getRoles() ) );
        usuarioDto.setUserId( usuario.getUserId() );
        usuarioDto.setNameUser( usuario.getNameUser() );
        usuarioDto.setContraseña( usuario.getContraseña() );
        usuarioDto.setFechaRegistro( usuario.getFechaRegistro() );
        if ( usuario.getActivo() != null ) {
            usuarioDto.setActivo( usuario.getActivo() );
        }

        return usuarioDto;
    }

    @Override
    public List<UsuarioDto> toUsuarioDtoList(List<Usuario> usuarios) {
        if ( usuarios == null ) {
            return null;
        }

        List<UsuarioDto> list = new ArrayList<UsuarioDto>( usuarios.size() );
        for ( Usuario usuario : usuarios ) {
            list.add( toUsuarioDto( usuario ) );
        }

        return list;
    }

    @Override
    public Usuario toUsuario(UsuarioDto usuarioDto) {
        if ( usuarioDto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setPersona( personaMapper.toPersona( usuarioDto.getPersonDto() ) );
        usuario.setRoles( rolesDtoListToRolSet( usuarioDto.getRoles() ) );
        usuario.setUserId( usuarioDto.getUserId() );
        usuario.setNameUser( usuarioDto.getNameUser() );
        usuario.setContraseña( usuarioDto.getContraseña() );
        usuario.setFechaRegistro( usuarioDto.getFechaRegistro() );
        usuario.setActivo( usuarioDto.isActivo() );

        return usuario;
    }

    @Override
    public List<Usuario> toUsuarioList(List<UsuarioDto> usuarioDtos) {
        if ( usuarioDtos == null ) {
            return null;
        }

        List<Usuario> list = new ArrayList<Usuario>( usuarioDtos.size() );
        for ( UsuarioDto usuarioDto : usuarioDtos ) {
            list.add( toUsuario( usuarioDto ) );
        }

        return list;
    }

    protected List<RolesDto> rolSetToRolesDtoList(Set<Rol> set) {
        if ( set == null ) {
            return null;
        }

        List<RolesDto> list = new ArrayList<RolesDto>( set.size() );
        for ( Rol rol : set ) {
            list.add( rolMapper.toRolesDto( rol ) );
        }

        return list;
    }

    protected Set<Rol> rolesDtoListToRolSet(List<RolesDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<Rol> set = LinkedHashSet.newLinkedHashSet( list.size() );
        for ( RolesDto rolesDto : list ) {
            set.add( rolMapper.toRol( rolesDto ) );
        }

        return set;
    }
}
