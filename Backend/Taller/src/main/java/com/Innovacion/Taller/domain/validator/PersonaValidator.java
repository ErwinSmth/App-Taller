package com.Innovacion.Taller.domain.validator;

import com.Innovacion.Taller.domain.dto.persona.PersonaDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.persona.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PersonaValidator implements Validator<PersonaDto> {

    @Autowired
    private IPersonaRepository personRepo;

    @Override
    public void validar(PersonaDto dto) {

        if (dto.getFechaNacimiento().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura");
        }
        if (personRepo.existsByDNI(dto.getDni())){
            throw new IllegalArgumentException("Ya existe una persona con ese DNI");
        }
        if (personRepo.existsByEmail(dto.getEmail())){
            throw new IllegalArgumentException("Ya exista una persona con ese Email");
        }
    }
}
