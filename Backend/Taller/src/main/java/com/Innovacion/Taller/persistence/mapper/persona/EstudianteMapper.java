package com.Innovacion.Taller.persistence.mapper.persona;

import com.Innovacion.Taller.domain.dto.persona.EstudianteDto;
import com.Innovacion.Taller.persistence.entity.persona.Estudiante;
import com.Innovacion.Taller.persistence.mapper.usuario.UsuarioMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})

public interface EstudianteMapper {
    @Mapping(source = "usuario", target ="usuarioDto")
    EstudianteDto toEstudianteDto(Estudiante estudiante);

    List <EstudianteDto> toEstudianteDtoList(List<Estudiante> estudiantes);

    @InheritInverseConfiguration
    @Mapping(source = "usuarioDto", target = "usuario")
    Estudiante toEstudiante(EstudianteDto estudianteDto);

    List <Estudiante> toEstudianteList(List<EstudianteDto> estudianteDtos);
}
