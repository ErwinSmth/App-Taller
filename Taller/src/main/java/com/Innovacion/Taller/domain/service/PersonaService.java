package com.Innovacion.Taller.domain.service;

import com.Innovacion.Taller.domain.dto.PersonaDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private IPersonaRepository personRepo;

    public PersonaDto registrarPersona(PersonaDto person){
        return personRepo.save(person);
    }

    public Optional<PersonaDto> buscarEmail(String email){
        return personRepo.findByEmail(email);
    }

}
