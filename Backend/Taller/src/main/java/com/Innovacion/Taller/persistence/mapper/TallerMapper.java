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
    @Mapping(source = "categoria.categoriaId", target = "idCategoria")
    Taller toTaller(TallerDto tallerDto);

    // Mapeo del Taller Resumido
    @Mapping(source = "idCategoria", target = "categoriaId")
    @Mapping(source = "categoria.nombre", target = "categoriaNombre")
    @Mapping(source = "profesor.profesorId", target = "profesorId")
    @Mapping(source = "profesor.usuario.persona.nombres", target = "profesorNombre")
    @Mapping(source = "organizador.organizadorId", target = "organizadorId")
    @Mapping(source = "organizador.razonSocial", target = "organizadorNombre")
    TallerResumenDto toTallerResumenDto(Taller taller);

}
