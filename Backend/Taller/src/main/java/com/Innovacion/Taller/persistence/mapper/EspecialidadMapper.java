package com.Innovacion.Taller.persistence.mapper;

import com.Innovacion.Taller.domain.dto.EspecialidadDto;
import com.Innovacion.Taller.persistence.entity.Especialidad;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EspecialidadMapper {
    EspecialidadDto toEspecialidadDto(Especialidad especialidad);
    Especialidad toEspecialidad(EspecialidadDto especialidadDto);
    List<EspecialidadDto> toEspecialidadDtoList(List<Especialidad> especialidades);
    List<Especialidad> toEspecialidadList(List<EspecialidadDto> especialidadesDto);
}
