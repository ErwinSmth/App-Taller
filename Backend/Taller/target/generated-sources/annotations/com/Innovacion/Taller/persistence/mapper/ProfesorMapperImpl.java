package com.Innovacion.Taller.persistence.mapper;

import com.Innovacion.Taller.domain.dto.ProfesorDto;
import com.Innovacion.Taller.persistence.entity.Profesor;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-08T16:52:37-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class ProfesorMapperImpl implements ProfesorMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;
    @Autowired
    private EspecialidadMapper especialidadMapper;

    @Override
    public ProfesorDto toProfesorDto(Profesor profesor) {
        if ( profesor == null ) {
            return null;
        }

        ProfesorDto profesorDto = new ProfesorDto();

        profesorDto.setUserDto( usuarioMapper.toUsuarioDto( profesor.getUsuario() ) );
        profesorDto.setEspecialidades( especialidadMapper.toEspecialidadDtoList( profesor.getEspecialidades() ) );
        profesorDto.setProfesorId( profesor.getProfesorId() );

        return profesorDto;
    }

    @Override
    public Profesor toProfesor(ProfesorDto profesorDto) {
        if ( profesorDto == null ) {
            return null;
        }

        Profesor profesor = new Profesor();

        profesor.setUsuario( usuarioMapper.toUsuario( profesorDto.getUserDto() ) );
        profesor.setEspecialidades( especialidadMapper.toEspecialidadList( profesorDto.getEspecialidades() ) );
        profesor.setProfesorId( profesorDto.getProfesorId() );

        return profesor;
    }
}
