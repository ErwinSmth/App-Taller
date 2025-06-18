package com.Innovacion.Taller.persistence.mapper.persona;


import com.Innovacion.Taller.domain.dto.persona.ProfesorDto;
import com.Innovacion.Taller.persistence.entity.persona.Profesor;
import com.Innovacion.Taller.persistence.mapper.EspecialidadMapper;
import com.Innovacion.Taller.persistence.mapper.usuario.UsuarioMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
