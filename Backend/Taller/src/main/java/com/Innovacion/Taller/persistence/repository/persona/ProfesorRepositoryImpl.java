package com.Innovacion.Taller.persistence.repository.persona;

import com.Innovacion.Taller.domain.dto.persona.ProfesorDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.persona.IProfesorRepository;
import com.Innovacion.Taller.persistence.crud.persona.ProfesorCrudRepository;
import com.Innovacion.Taller.persistence.entity.persona.Profesor;
import com.Innovacion.Taller.persistence.mapper.persona.ProfesorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProfesorRepositoryImpl implements IProfesorRepository {

    @Autowired
    private ProfesorCrudRepository profeCrud;
    @Autowired
    private ProfesorMapper mapper;

    @Override
    public ProfesorDto save(ProfesorDto profesorDto) {
        Profesor profesor = mapper.toProfesor(profesorDto);
        return mapper.toProfesorDto(profeCrud.save(profesor));
    }

    @Override
    public Optional<ProfesorDto> findById(Long id) {
        return profeCrud.findById(id).map(mapper::toProfesorDto);
    }

    @Override
    public Optional<ProfesorDto> findByUsuarioId(Long userId) {
        return profeCrud.findByUsuarioUserId(userId).map(mapper::toProfesorDto);
    }

    @Override
    public List<ProfesorDto> findByEspecialidad(String especialidad) {
        return profeCrud.findAll().stream()
                .filter(p -> p.getEspecialidades().stream()
                        .anyMatch(e -> e.getNombre().equalsIgnoreCase(especialidad)))
                .map(mapper::toProfesorDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Long> findPersonaIdByProfesorId(Long profesorId) {
        return profeCrud.findById(profesorId)
                .map(profesor -> {
                    if (profesor.getUsuario() != null && profesor.getUsuario().getPersona() != null){
                        return profesor.getUsuario().getPersona().getPersonaId();
                    } else {
                        return null;
                    }
                });
    }
}
