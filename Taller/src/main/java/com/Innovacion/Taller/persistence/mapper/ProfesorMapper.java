package com.Innovacion.Taller.persistence.mapper;


import com.Innovacion.Taller.domain.dto.ProfesorDto;
import com.Innovacion.Taller.persistence.entity.Profesor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {UsuarioMapper.class})

public interface ProfesorMapper {
    @Mapping(source = "usuario", target = "usuarioDto" )
    ProfesorDto toProfesorDto (Profesor profesor);
    List <ProfesorDto>toProfesorDtoList(List<Profesor>profesors);

    @InheritInverseConfiguration
    @Mapping(source = "usuarioDto", target = "usuario")
    Profesor toProfesor(ProfesorDto profesorDto);
    List <Profesor> toProfesorList(List<ProfesorDto>profesorDtos);

}

