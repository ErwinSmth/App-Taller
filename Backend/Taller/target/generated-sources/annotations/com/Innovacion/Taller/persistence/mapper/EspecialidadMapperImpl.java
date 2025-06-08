package com.Innovacion.Taller.persistence.mapper;

import com.Innovacion.Taller.domain.dto.EspecialidadDto;
import com.Innovacion.Taller.persistence.entity.Especialidad;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-08T16:52:38-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class EspecialidadMapperImpl implements EspecialidadMapper {

    @Override
    public EspecialidadDto toEspecialidadDto(Especialidad especialidad) {
        if ( especialidad == null ) {
            return null;
        }

        EspecialidadDto especialidadDto = new EspecialidadDto();

        especialidadDto.setEspecialidadId( especialidad.getEspecialidadId() );
        especialidadDto.setNombre( especialidad.getNombre() );
        especialidadDto.setDescripcion( especialidad.getDescripcion() );

        return especialidadDto;
    }

    @Override
    public Especialidad toEspecialidad(EspecialidadDto especialidadDto) {
        if ( especialidadDto == null ) {
            return null;
        }

        Especialidad especialidad = new Especialidad();

        especialidad.setEspecialidadId( especialidadDto.getEspecialidadId() );
        especialidad.setNombre( especialidadDto.getNombre() );
        especialidad.setDescripcion( especialidadDto.getDescripcion() );

        return especialidad;
    }

    @Override
    public List<EspecialidadDto> toEspecialidadDtoList(List<Especialidad> especialidades) {
        if ( especialidades == null ) {
            return null;
        }

        List<EspecialidadDto> list = new ArrayList<EspecialidadDto>( especialidades.size() );
        for ( Especialidad especialidad : especialidades ) {
            list.add( toEspecialidadDto( especialidad ) );
        }

        return list;
    }

    @Override
    public List<Especialidad> toEspecialidadList(List<EspecialidadDto> especialidadesDto) {
        if ( especialidadesDto == null ) {
            return null;
        }

        List<Especialidad> list = new ArrayList<Especialidad>( especialidadesDto.size() );
        for ( EspecialidadDto especialidadDto : especialidadesDto ) {
            list.add( toEspecialidad( especialidadDto ) );
        }

        return list;
    }
}
