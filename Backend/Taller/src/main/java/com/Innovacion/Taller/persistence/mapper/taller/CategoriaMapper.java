package com.Innovacion.Taller.persistence.mapper.taller;

import com.Innovacion.Taller.domain.dto.taller.CategoriaDto;
import com.Innovacion.Taller.persistence.entity.taller.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaDto toCategoriaDto(Categoria categoria);

    @InheritInverseConfiguration
    Categoria toCategoria(CategoriaDto categoriaDto);

}
