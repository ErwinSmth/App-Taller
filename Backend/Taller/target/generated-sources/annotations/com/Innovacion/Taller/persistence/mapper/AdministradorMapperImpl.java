package com.Innovacion.Taller.persistence.mapper;

import com.Innovacion.Taller.domain.dto.AdministradorDto;
import com.Innovacion.Taller.persistence.entity.Administrador;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-08T16:52:38-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class AdministradorMapperImpl implements AdministradorMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public AdministradorDto toAdministradorDto(Administrador admin) {
        if ( admin == null ) {
            return null;
        }

        AdministradorDto administradorDto = new AdministradorDto();

        administradorDto.setUserDto( usuarioMapper.toUsuarioDto( admin.getUsuario() ) );
        administradorDto.setAdministradorId( admin.getAdministradorId() );

        return administradorDto;
    }

    @Override
    public Administrador toAdministrador(AdministradorDto adminDto) {
        if ( adminDto == null ) {
            return null;
        }

        Administrador administrador = new Administrador();

        administrador.setUsuario( usuarioMapper.toUsuario( adminDto.getUserDto() ) );
        administrador.setAdministradorId( adminDto.getAdministradorId() );

        return administrador;
    }
}
