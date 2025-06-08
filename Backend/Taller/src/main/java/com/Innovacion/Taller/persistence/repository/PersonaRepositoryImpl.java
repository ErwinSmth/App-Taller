package com.Innovacion.Taller.persistence.repository;

import com.Innovacion.Taller.domain.dto.PersonaDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.IPersonaRepository;
import com.Innovacion.Taller.persistence.crud.PersonaCrudRepository;
import com.Innovacion.Taller.persistence.entity.Persona;
import com.Innovacion.Taller.persistence.mapper.PersonaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PersonaRepositoryImpl implements IPersonaRepository {

    @Autowired
    private PersonaCrudRepository personCrud ;  //Para acceder a la bd
    @Autowired
    private PersonaMapper personaMapper;  //Para convertir Dto a entidad o viceversa

    @Override
    public PersonaDto save(PersonaDto personDto) {
        Persona persona = personaMapper.toPersona(personDto);
        return personaMapper.toPersonaDto(personCrud.save(persona));
    }

    @Override
    public Optional<PersonaDto> findByEmail(String email) {
        return personCrud.findByEmail(email).map(Persona -> personaMapper.toPersonaDto(Persona));
    }

    @Override
    public boolean existsByDNI(String dni) {
        return personCrud.existsByDNI(dni);
    }

    @Override
    public boolean existsByEmail(String email) {
        return personCrud.existsByEmail(email);
    }

    @Override
    public Optional<PersonaDto> findById(Long id) {
        return personCrud.findById(id).map( persona -> personaMapper.toPersonaDto(persona));
    }
}
