package com.Innovacion.Taller.domain.repositoryInterfaces.persona;

import com.Innovacion.Taller.domain.dto.persona.PersonaDto;

import java.util.Optional;

public interface IPersonaRepository {

    PersonaDto save(PersonaDto person);
    Optional<PersonaDto> findByEmail(String email);
    boolean existsByDNI(String dni);
    boolean existsByEmail(String email);
    Optional<PersonaDto> findById(Long id);
    Optional<PersonaDto> findByDNI(String dni);
}
