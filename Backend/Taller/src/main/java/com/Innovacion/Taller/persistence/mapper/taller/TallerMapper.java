package com.Innovacion.Taller.persistence.mapper.taller;

import com.Innovacion.Taller.domain.dto.taller.TallerDto;
import com.Innovacion.Taller.domain.dto.taller.TallerResumenDto;
import com.Innovacion.Taller.persistence.entity.taller.Taller;
import com.Innovacion.Taller.persistence.mapper.persona.OrganizadorMapper;
import com.Innovacion.Taller.persistence.mapper.persona.ProfesorMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CategoriaMapper.class, ProfesorMapper.class, OrganizadorMapper.class, TallerImagenMapper.class})
public interface TallerMapper {

    //Mapeo completo
    @Mapping(target = "fechaFinalizacion", source = "fechaFinalizacion")
    TallerDto toTallerDto(Taller taller);

    @InheritInverseConfiguration
    @Mapping(source = "categoria.categoriaId", target = "idCategoria")
    @Mapping(target = "imagenes", source = "imagenes")
    @Mapping(target = "fechaFinalizacion", source = "fechaFinalizacion")
    Taller toTaller(TallerDto tallerDto);

    // Mapeo del Taller Resumido
    @Mapping(source = "idCategoria", target = "categoriaId")
    @Mapping(source = "categoria.nombre", target = "categoriaNombre")
    @Mapping(source = "profesor.profesorId", target = "profesorId")
    @Mapping(source = "profesor.usuario.persona.nombres", target = "profesorNombre")
    @Mapping(source = "organizador.organizadorId", target = "organizadorId")
    @Mapping(source = "organizador.razonSocial", target = "organizadorNombre")
    @Mapping(target = "imagenes", source = "imagenes")
    @Mapping(target = "fechaFinalizacion", source = "fechaFinalizacion")
    TallerResumenDto toTallerResumenDto(Taller taller);

}
