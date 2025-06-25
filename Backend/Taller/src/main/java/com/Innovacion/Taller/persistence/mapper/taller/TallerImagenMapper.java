package com.Innovacion.Taller.persistence.mapper.taller;

import com.Innovacion.Taller.domain.dto.taller.TallerImagenDto;
import com.Innovacion.Taller.persistence.entity.taller.TallerImagen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TallerImagenMapper {
    @Mapping(source = "taller.tallerId", target = "tallerId")
    TallerImagenDto toTallerImagenDto(TallerImagen entity);

    @Mapping(source = "tallerId", target = "taller.tallerId")
    TallerImagen toTallerImagen(TallerImagenDto dto);
}
