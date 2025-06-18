package com.Innovacion.Taller.domain.repositoryInterfaces.taller;

import com.Innovacion.Taller.domain.dto.taller.CategoriaDto;

import java.util.List;
import java.util.Optional;

public interface ICategoriaRepository {

    CategoriaDto save(CategoriaDto categoria);
    Optional<CategoriaDto> findById(Long id);
    List<CategoriaDto> findAll();
    Optional<CategoriaDto> findByNombre(String nombre);
    void deleteById(Long id);

}
