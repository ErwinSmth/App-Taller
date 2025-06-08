package com.Innovacion.Taller.persistence.mapper;

import com.Innovacion.Taller.domain.dto.CategoriaDto;
import com.Innovacion.Taller.domain.dto.TallerDto;
import com.Innovacion.Taller.domain.dto.TallerResumenDto;
import com.Innovacion.Taller.persistence.entity.Categoria;
import com.Innovacion.Taller.persistence.entity.Organizador;
import com.Innovacion.Taller.persistence.entity.Persona;
import com.Innovacion.Taller.persistence.entity.Profesor;
import com.Innovacion.Taller.persistence.entity.Taller;
import com.Innovacion.Taller.persistence.entity.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-07T23:17:44-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class TallerMapperImpl implements TallerMapper {

    @Autowired
    private CategoriaMapper categoriaMapper;
    @Autowired
    private ProfesorMapper profesorMapper;
    @Autowired
    private OrganizadorMapper organizadorMapper;

    @Override
    public TallerDto toTallerDto(Taller taller) {
        if ( taller == null ) {
            return null;
        }

        TallerDto tallerDto = new TallerDto();

        tallerDto.setTallerId( taller.getTallerId() );
        tallerDto.setTitulo( taller.getTitulo() );
        tallerDto.setDescripcion( taller.getDescripcion() );
        tallerDto.setDuracionHoras( taller.getDuracionHoras() );
        tallerDto.setPrecio( taller.getPrecio() );
        tallerDto.setCapacidad( taller.getCapacidad() );
        tallerDto.setFechaRegistro( taller.getFechaRegistro() );
        tallerDto.setImagenUrl( taller.getImagenUrl() );
        tallerDto.setCategoria( categoriaMapper.toCategoriaDto( taller.getCategoria() ) );
        tallerDto.setProfesor( profesorMapper.toProfesorDto( taller.getProfesor() ) );
        tallerDto.setOrganizador( organizadorMapper.toOrganizadorDto( taller.getOrganizador() ) );

        return tallerDto;
    }

    @Override
    public Taller toTaller(TallerDto tallerDto) {
        if ( tallerDto == null ) {
            return null;
        }

        Taller taller = new Taller();

        taller.setIdCategoria( tallerDtoCategoriaCategoriaId( tallerDto ) );
        taller.setTallerId( tallerDto.getTallerId() );
        taller.setCategoria( categoriaMapper.toCategoria( tallerDto.getCategoria() ) );
        taller.setOrganizador( organizadorMapper.toOrganizador( tallerDto.getOrganizador() ) );
        taller.setProfesor( profesorMapper.toProfesor( tallerDto.getProfesor() ) );
        taller.setTitulo( tallerDto.getTitulo() );
        taller.setDescripcion( tallerDto.getDescripcion() );
        taller.setDuracionHoras( tallerDto.getDuracionHoras() );
        taller.setPrecio( tallerDto.getPrecio() );
        taller.setCapacidad( tallerDto.getCapacidad() );
        taller.setFechaRegistro( tallerDto.getFechaRegistro() );
        taller.setImagenUrl( tallerDto.getImagenUrl() );

        return taller;
    }

    @Override
    public TallerResumenDto toTallerResumenDto(Taller taller) {
        if ( taller == null ) {
            return null;
        }

        TallerResumenDto tallerResumenDto = new TallerResumenDto();

        tallerResumenDto.setCategoriaId( taller.getIdCategoria() );
        tallerResumenDto.setCategoriaNombre( tallerCategoriaNombre( taller ) );
        tallerResumenDto.setProfesorId( tallerProfesorProfesorId( taller ) );
        tallerResumenDto.setProfesorNombre( tallerProfesorUsuarioPersonaNombres( taller ) );
        tallerResumenDto.setOrganizadorId( tallerOrganizadorOrganizadorId( taller ) );
        tallerResumenDto.setOrganizadorNombre( tallerOrganizadorRazonSocial( taller ) );
        tallerResumenDto.setTallerId( taller.getTallerId() );
        tallerResumenDto.setTitulo( taller.getTitulo() );
        tallerResumenDto.setImagenUrl( taller.getImagenUrl() );
        tallerResumenDto.setCapacidad( taller.getCapacidad() );
        tallerResumenDto.setPrecio( taller.getPrecio() );

        return tallerResumenDto;
    }

    private Long tallerDtoCategoriaCategoriaId(TallerDto tallerDto) {
        CategoriaDto categoria = tallerDto.getCategoria();
        if ( categoria == null ) {
            return null;
        }
        return categoria.getCategoriaId();
    }

    private String tallerCategoriaNombre(Taller taller) {
        Categoria categoria = taller.getCategoria();
        if ( categoria == null ) {
            return null;
        }
        return categoria.getNombre();
    }

    private Long tallerProfesorProfesorId(Taller taller) {
        Profesor profesor = taller.getProfesor();
        if ( profesor == null ) {
            return null;
        }
        return profesor.getProfesorId();
    }

    private String tallerProfesorUsuarioPersonaNombres(Taller taller) {
        Profesor profesor = taller.getProfesor();
        if ( profesor == null ) {
            return null;
        }
        Usuario usuario = profesor.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        Persona persona = usuario.getPersona();
        if ( persona == null ) {
            return null;
        }
        return persona.getNombres();
    }

    private Long tallerOrganizadorOrganizadorId(Taller taller) {
        Organizador organizador = taller.getOrganizador();
        if ( organizador == null ) {
            return null;
        }
        return organizador.getOrganizadorId();
    }

    private String tallerOrganizadorRazonSocial(Taller taller) {
        Organizador organizador = taller.getOrganizador();
        if ( organizador == null ) {
            return null;
        }
        return organizador.getRazonSocial();
    }
}
