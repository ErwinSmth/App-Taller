package com.Innovacion.Taller.persistence.mapper;

import com.Innovacion.Taller.domain.dto.TallerDto;
import com.Innovacion.Taller.domain.dto.TallerResumenDto;
import com.Innovacion.Taller.persistence.entity.Taller;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CategoriaMapper.class, ProfesorMapper.class, OrganizadorMapper.class})
public interface TallerMapper {

    //Mapeo completo
    TallerDto toTallerDto(Taller taller);

    @InheritInverseConfiguration
    @Mapping(target = "idCategoria", ignore = true)
    Taller toTaller(TallerDto tallerDto);

    //Mapeo del Taller Resumido
    // Mapeo del Taller Resumido
    @Mapping(source = "idCategoria", target = "categoriaId")
    @Mapping(source = "categoria.nombre", target = "categoriaNombre")
    @Mapping(source = "profesor.profesorId", target = "profesorId")
    @Mapping(source = "profesor.userDto.personDto.nombres", target = "profesorNombre")
    @Mapping(source = "organizador.organizadorId", target = "organizadorId")
    @Mapping(source = "organizador.razonSocial", target = "organizadorNombre")
    TallerResumenDto toTallerResumenDto(Taller taller);

}
