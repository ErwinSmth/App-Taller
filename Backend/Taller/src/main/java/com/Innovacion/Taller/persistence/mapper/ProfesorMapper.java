package com.Innovacion.Taller.persistence.mapper;


import com.Innovacion.Taller.domain.dto.ProfesorDto;
import com.Innovacion.Taller.persistence.entity.Profesor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring",uses = {UsuarioMapper.class, EspecialidadMapper.class})

public interface ProfesorMapper {
    @Mapping(source = "usuario", target = "userDto")
    @Mapping(source = "especialidades", target = "especialidades")
    @Mapping(source = "descripcion", target = "descripcion")
    ProfesorDto toProfesorDto(Profesor profesor);

    @InheritInverseConfiguration
    @Mapping(source = "descripcion", target = "descripcion")
    Profesor toProfesor(ProfesorDto profesorDto);

}
