package com.Innovacion.Taller.persistence.mapper;

import com.Innovacion.Taller.domain.dto.OrganizadorDto;
import com.Innovacion.Taller.persistence.entity.Organizador;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-08T16:52:38-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class OrganizadorMapperImpl implements OrganizadorMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public OrganizadorDto toOrganizadorDto(Organizador organizador) {
        if ( organizador == null ) {
            return null;
        }

        OrganizadorDto organizadorDto = new OrganizadorDto();

        organizadorDto.setUserDto( usuarioMapper.toUsuarioDto( organizador.getUsuario() ) );
        organizadorDto.setOrganizadorId( organizador.getOrganizadorId() );
        organizadorDto.setRazonSocial( organizador.getRazonSocial() );
        organizadorDto.setRuc( organizador.getRuc() );
        organizadorDto.setDescripcion( organizador.getDescripcion() );
        organizadorDto.setDireccionSede( organizador.getDireccionSede() );

        return organizadorDto;
    }

    @Override
    public Organizador toOrganizador(OrganizadorDto organizadorDto) {
        if ( organizadorDto == null ) {
            return null;
        }

        Organizador organizador = new Organizador();

        organizador.setUsuario( usuarioMapper.toUsuario( organizadorDto.getUserDto() ) );
        organizador.setOrganizadorId( organizadorDto.getOrganizadorId() );
        organizador.setRazonSocial( organizadorDto.getRazonSocial() );
        organizador.setRuc( organizadorDto.getRuc() );
        organizador.setDescripcion( organizadorDto.getDescripcion() );
        organizador.setDireccionSede( organizadorDto.getDireccionSede() );

        return organizador;
    }
}
