package com.Innovacion.Taller.persistence.mapper;

import com.Innovacion.Taller.domain.dto.ProfesorDto;
import com.Innovacion.Taller.persistence.entity.Profesor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public interface ProfesorMapper {

    @Mapping(source = "usuario", target = "userDto")
    ProfesorDto toProfesorDto(Profesor profesor);

    @InheritInverseConfiguration
    @Mapping(source = "userDto", target = "usuario")
    Profesor toProfesor(ProfesorDto profesorDto);

}
