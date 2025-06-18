package com.Innovacion.Taller.persistence.repository.persona;

import com.Innovacion.Taller.domain.dto.persona.PersonaDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.persona.IPersonaRepository;
import com.Innovacion.Taller.persistence.crud.persona.PersonaCrudRepository;
import com.Innovacion.Taller.persistence.entity.persona.Persona;
import com.Innovacion.Taller.persistence.mapper.persona.PersonaMapper;
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

    @Override
    public Optional<PersonaDto> findByDNI(String dni) {
        return personCrud.findByDNI(dni).map(persona -> personaMapper.toPersonaDto(persona));
    }

}
