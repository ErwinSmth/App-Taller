package com.Innovacion.Taller.persistence.repository.taller;

import com.Innovacion.Taller.domain.dto.taller.CategoriaDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.taller.ICategoriaRepository;
import com.Innovacion.Taller.persistence.crud.taller.CategoriaCrudRepository;
import com.Innovacion.Taller.persistence.entity.taller.Categoria;
import com.Innovacion.Taller.persistence.mapper.taller.CategoriaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CategoriaRepositoryImpl implements ICategoriaRepository {

    @Autowired
    private CategoriaCrudRepository categoriaCrud;

    @Autowired
    private CategoriaMapper mapper;

    @Override
    public CategoriaDto save(CategoriaDto categoria) {
        Categoria catego = mapper.toCategoria(categoria);
        return mapper.toCategoriaDto(categoriaCrud.save(catego));
    }

    @Override
    public Optional<CategoriaDto> findById(Long id) {
        return categoriaCrud.findById(id).map(mapper::toCategoriaDto);
    }

    @Override
    public List<CategoriaDto> findAll() {
        return categoriaCrud.findAll().stream()
                .map(mapper::toCategoriaDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoriaDto> findByNombre(String nombre) {
        return categoriaCrud.findByNombre(nombre).map(mapper::toCategoriaDto);
    }

    @Override
    public void deleteById(Long id) {
        categoriaCrud.deleteById(id);
    }
}
