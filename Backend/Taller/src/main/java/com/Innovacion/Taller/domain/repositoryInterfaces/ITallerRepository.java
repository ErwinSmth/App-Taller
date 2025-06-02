package com.Innovacion.Taller.domain.repositoryInterfaces;

import com.Innovacion.Taller.domain.dto.TallerDto;
import com.Innovacion.Taller.domain.dto.TallerResumenDto;

import java.util.List;
import java.util.Optional;

public interface ITallerRepository {

    TallerDto save(TallerDto taller);
    Optional<TallerDto> findById(Long id);
    void deteleById(Long id);

    //Para el taller resumen que solo servira para la parte visual
    List<TallerResumenDto> findAll();
    List<TallerResumenDto> findByCategoriaId(Long CategoriaId);
    List<TallerResumenDto> findProfesorId(Long profesorId);
    List<TallerResumenDto> findByOrganizadorId(Long organizadorId);
    List<TallerResumenDto> findByTituloContaining(String titulo);

}
