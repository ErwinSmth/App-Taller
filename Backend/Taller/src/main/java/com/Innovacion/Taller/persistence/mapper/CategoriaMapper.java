package com.Innovacion.Taller.persistence.mapper;

import com.Innovacion.Taller.domain.dto.CategoriaDto;
import com.Innovacion.Taller.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaDto toCategoriaDto(Categoria categoria);

    @InheritInverseConfiguration
    Categoria toCategoria(CategoriaDto categoriaDto);

}
