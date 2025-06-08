package com.Innovacion.Taller.persistence.mapper;

import com.Innovacion.Taller.domain.dto.CategoriaDto;
import com.Innovacion.Taller.persistence.entity.Categoria;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-07T23:17:44-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class CategoriaMapperImpl implements CategoriaMapper {

    @Override
    public CategoriaDto toCategoriaDto(Categoria categoria) {
        if ( categoria == null ) {
            return null;
        }

        CategoriaDto categoriaDto = new CategoriaDto();

        categoriaDto.setCategoriaId( categoria.getCategoriaId() );
        categoriaDto.setNombre( categoria.getNombre() );
        categoriaDto.setDescripcion( categoria.getDescripcion() );

        return categoriaDto;
    }

    @Override
    public Categoria toCategoria(CategoriaDto categoriaDto) {
        if ( categoriaDto == null ) {
            return null;
        }

        Categoria categoria = new Categoria();

        categoria.setCategoriaId( categoriaDto.getCategoriaId() );
        categoria.setNombre( categoriaDto.getNombre() );
        categoria.setDescripcion( categoriaDto.getDescripcion() );

        return categoria;
    }
}
