package com.Innovacion.Taller.persistence.repository;

import com.Innovacion.Taller.domain.repositoryInterfaces.IPersonaRepository;
import com.Innovacion.Taller.persistence.crud.PersonaCrudRepository;
import com.Innovacion.Taller.persistence.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PersonaRepositoryImpl implements IPersonaRepository {

    @Autowired
    private PersonaCrudRepository personCrud;

    @Override
    public Persona save(Persona person) {
        return personCrud.save(person);
    }

    @Override
    public Optional<Persona> findByEmail(String email) {
        return personCrud.findByEmail(email);
    }
}
