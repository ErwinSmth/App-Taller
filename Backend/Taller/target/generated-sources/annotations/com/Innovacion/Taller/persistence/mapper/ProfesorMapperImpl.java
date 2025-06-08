package com.Innovacion.Taller.persistence.mapper;

import com.Innovacion.Taller.domain.dto.ProfesorDto;
import com.Innovacion.Taller.persistence.entity.Profesor;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-02T13:40:22-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class ProfesorMapperImpl implements ProfesorMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public ProfesorDto toProfesorDto(Profesor profesor) {
        if ( profesor == null ) {
            return null;
        }

        ProfesorDto profesorDto = new ProfesorDto();

        profesorDto.setUserDto( usuarioMapper.toUsuarioDto( profesor.getUsuario() ) );
        profesorDto.setProfesorId( profesor.getProfesorId() );
        profesorDto.setDescripcion( profesor.getDescripcion() );
        profesorDto.setEspecialidades( profesor.getEspecialidades() );

        return profesorDto;
    }

    @Override
    public Profesor toProfesor(ProfesorDto profesorDto) {
        if ( profesorDto == null ) {
            return null;
        }

        Profesor profesor = new Profesor();

        profesor.setUsuario( usuarioMapper.toUsuario( profesorDto.getUserDto() ) );
        profesor.setProfesorId( profesorDto.getProfesorId() );
        profesor.setDescripcion( profesorDto.getDescripcion() );
        profesor.setEspecialidades( profesorDto.getEspecialidades() );

        return profesor;
    }
}
