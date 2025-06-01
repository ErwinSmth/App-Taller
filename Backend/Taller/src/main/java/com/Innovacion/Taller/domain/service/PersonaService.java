package com.Innovacion.Taller.domain.service;

import com.Innovacion.Taller.domain.dto.PersonaDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.IPersonaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private IPersonaRepository personRepo;

    @Transactional
    public PersonaDto registrarPersona(PersonaDto person){

        //Validar si ya existe la persona por DNI o Correo
        if (personRepo.existsByDNI(person.getDNI())){
            throw  new IllegalArgumentException("Ya existe una Persona con ese DNI");
        }
        if (personRepo.existsByEmail(person.getEmail())){
            throw  new IllegalArgumentException("Ya existe una persona con ese Email");
        }

        return personRepo.save(person);
    }

    public Optional<PersonaDto> buscarEmail(String email){
        return personRepo.findByEmail(email);
    }

}
