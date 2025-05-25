package com.Innovacion.Taller.domain.repositoryInterfaces;

import com.Innovacion.Taller.domain.dto.PersonaDto;
import com.Innovacion.Taller.persistence.entity.Persona;

import java.util.Optional;

public interface IPersonaRepository {

    PersonaDto save(PersonaDto person);
    Optional<PersonaDto> findByEmail(String email);

}
