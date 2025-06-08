package com.Innovacion.Taller.persistence.mapper;

import com.Innovacion.Taller.domain.dto.EstudianteDto;
import com.Innovacion.Taller.persistence.entity.Estudiante;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-07T23:17:44-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class EstudianteMapperImpl implements EstudianteMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public EstudianteDto toEstudianteDto(Estudiante estudiante) {
        if ( estudiante == null ) {
            return null;
        }

        EstudianteDto estudianteDto = new EstudianteDto();

        estudianteDto.setUsuarioDto( usuarioMapper.toUsuarioDto( estudiante.getUsuario() ) );
        estudianteDto.setEstudianteId( estudiante.getEstudianteId() );
        estudianteDto.setIntereses( estudiante.getIntereses() );

        return estudianteDto;
    }

    @Override
    public List<EstudianteDto> toEstudianteDtoList(List<Estudiante> estudiantes) {
        if ( estudiantes == null ) {
            return null;
        }

        List<EstudianteDto> list = new ArrayList<EstudianteDto>( estudiantes.size() );
        for ( Estudiante estudiante : estudiantes ) {
            list.add( toEstudianteDto( estudiante ) );
        }

        return list;
    }

    @Override
    public Estudiante toEstudiante(EstudianteDto estudianteDto) {
        if ( estudianteDto == null ) {
            return null;
        }

        Estudiante estudiante = new Estudiante();

        estudiante.setUsuario( usuarioMapper.toUsuario( estudianteDto.getUsuarioDto() ) );
        estudiante.setEstudianteId( estudianteDto.getEstudianteId() );
        estudiante.setIntereses( estudianteDto.getIntereses() );

        return estudiante;
    }

    @Override
    public List<Estudiante> toEstudianteList(List<EstudianteDto> estudianteDtos) {
        if ( estudianteDtos == null ) {
            return null;
        }

        List<Estudiante> list = new ArrayList<Estudiante>( estudianteDtos.size() );
        for ( EstudianteDto estudianteDto : estudianteDtos ) {
            list.add( toEstudiante( estudianteDto ) );
        }

        return list;
    }
}
