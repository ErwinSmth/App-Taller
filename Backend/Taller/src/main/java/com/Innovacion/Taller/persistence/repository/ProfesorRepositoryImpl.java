package com.Innovacion.Taller.persistence.repository;

import com.Innovacion.Taller.domain.dto.ProfesorDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.IProfesorRepository;
import com.Innovacion.Taller.persistence.crud.ProfesorCrudRepository;
import com.Innovacion.Taller.persistence.entity.Profesor;
import com.Innovacion.Taller.persistence.mapper.ProfesorMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProfesorRepositoryImpl implements IProfesorRepository {

    @Autowired
    private ProfesorCrudRepository profesorCrud;

    @Autowired
    private ProfesorMapper mapper;

    @Override
    public ProfesorDto save(ProfesorDto profesor) {
        Profesor profe = mapper.toProfesor(profesor);
        return mapper.toProfesorDto(profesorCrud.save(profe));
    }

    @Override
    public Optional<ProfesorDto> findByUsuarioId(Long userId) {
        return profesorCrud.findByUsuarioUserId(userId).map(mapper::toProfesorDto);
    }

    @Override
    public List<ProfesorDto> findByEspecialidad(String especialidad) {
        return profesorCrud.findByEspecialidadesContaining(especialidad)
                .stream()
                .map(mapper::toProfesorDto)
                .collect(Collectors.toList());
    }
}
