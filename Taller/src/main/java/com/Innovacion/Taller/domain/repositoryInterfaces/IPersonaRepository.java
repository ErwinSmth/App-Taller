package com.Innovacion.Taller.domain.repositoryInterfaces;

import com.Innovacion.Taller.persistence.entity.Persona;

import java.util.Optional;

public interface IPersonaRepository {

    Persona save(Persona person);
    Optional<Persona> findByEmail(String email);

}
