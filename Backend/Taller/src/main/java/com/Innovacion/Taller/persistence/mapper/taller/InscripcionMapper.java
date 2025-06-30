package com.Innovacion.Taller.persistence.mapper.taller;

import com.Innovacion.Taller.domain.dto.taller.InscripcionDto;
import com.Innovacion.Taller.persistence.entity.taller.Inscripcion;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InscripcionMapper {

    @Mapping(source = "taller.tallerId", target = "tallerId")
    @Mapping(source = "estudiante.estudianteId", target = "estudianteId")
    InscripcionDto toInscripcionDto(Inscripcion inscripcion);

    @InheritInverseConfiguration
    @Mapping(target = "taller", ignore = true) // Se debe setear manualmente en el repo
    @Mapping(target = "estudiante", ignore = true) // Se debe setear manualmente en el repo
    Inscripcion toInscripcion(InscripcionDto dto);

}
