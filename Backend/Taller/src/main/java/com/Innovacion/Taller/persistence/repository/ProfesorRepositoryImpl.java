package com.Innovacion.Taller.persistence.repository;

import com.Innovacion.Taller.domain.dto.ProfesorDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.IProfesorRepository;
import com.Innovacion.Taller.persistence.crud.ProfesorCrudRepository;
import com.Innovacion.Taller.persistence.entity.Profesor;
import com.Innovacion.Taller.persistence.mapper.ProfesorMapper;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD:Taller/src/main/java/com/Innovacion/Taller/persistence/repository/ProfesorRepositoryImpl.java

import java.util.Optional;

public class ProfesorRepositoryImpl implements IProfesorRepository {

    @Autowired
    private ProfesorCrudRepository profeCrud;
    @Autowired
    private ProfesorMapper mapper;
    @Override
    public ProfesorDto save(ProfesorDto profesorDto) {
        Profesor profesor=mapper.toProfesor(profesorDto);

        return mapper.toProfesorDto(profeCrud.save(profesor));
    }

    @Override
    public Optional<ProfesorDto> findByEspecialidades(String especialidades) {
        return profeCrud.findByEspecialidades(especialidades).map(Profesor->mapper.toProfesorDto(Profesor));
=======
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
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
    public Optional<ProfesorDto> findById(Long id) {
        return profesorCrud.findById(id).map(mapper::toProfesorDto);
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
>>>>>>> main:Backend/Taller/src/main/java/com/Innovacion/Taller/persistence/repository/ProfesorRepositoryImpl.java
    }
}
