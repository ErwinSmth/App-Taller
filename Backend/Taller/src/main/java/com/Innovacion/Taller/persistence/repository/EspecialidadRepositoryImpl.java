package com.Innovacion.Taller.persistence.repository;

import com.Innovacion.Taller.domain.dto.EspecialidadDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.IEspecialidadRepository;
import com.Innovacion.Taller.persistence.crud.EspecialidadCrudRepository;
import com.Innovacion.Taller.persistence.entity.Especialidad;
import com.Innovacion.Taller.persistence.mapper.EspecialidadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EspecialidadRepositoryImpl implements IEspecialidadRepository {


    @Autowired
    private EspecialidadCrudRepository crud;

    @Autowired
    private EspecialidadMapper mapper;

    @Override
    public EspecialidadDto save(EspecialidadDto especialidad) {
        Especialidad entity = mapper.toEspecialidad(especialidad);
        return mapper.toEspecialidadDto(crud.save(entity));
    }

    @Override
    public Optional<EspecialidadDto> findById(Long id) {
        return crud.findById(id).map(mapper::toEspecialidadDto);
    }

    @Override
    public List<EspecialidadDto> findAll() {
        return crud.findAll().stream()
                .map(mapper::toEspecialidadDto)
                .collect(Collectors.toList());
    }

}
