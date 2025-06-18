package com.Innovacion.Taller.domain.service;

import com.Innovacion.Taller.domain.dto.persona.PersonaDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.persona.IPersonaRepository;
import com.Innovacion.Taller.domain.validator.PersonaValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private IPersonaRepository personRepo;

    @Autowired
    private PersonaValidator validator;

    @Transactional
    public PersonaDto registrarPersona(PersonaDto person){

        validator.validar(person);

        return personRepo.save(person);
    }

    public Optional<PersonaDto> buscarEmail(String email){
        return personRepo.findByEmail(email);
    }

    @Transactional
    public PersonaDto editarPersona(Long id, PersonaDto persona){
        Optional<PersonaDto> existente = personRepo.findById(id);
        if (existente.isEmpty()) throw new IllegalArgumentException("Persona no encontrada");

        // Validar DNI
        Optional<PersonaDto> personaConDni = personRepo.findByDNI(persona.getDni());
        if (personaConDni.isPresent() && !personaConDni.get().getPersonaId().equals(id)) {
            throw new IllegalArgumentException("Ya existe una Persona con ese DNI");
        }

        // Validar Email
        Optional<PersonaDto> personaConEmail = personRepo.findByEmail(persona.getEmail());
        if (personaConEmail.isPresent() && !personaConEmail.get().getPersonaId().equals(id)) {
            throw new IllegalArgumentException("Ya existe una Persona con ese Email");
        }

        persona.setPersonaId(id);
        return personRepo.save(persona);
    }

}
