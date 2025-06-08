package com.Innovacion.Taller.domain.service;

import com.Innovacion.Taller.domain.dto.PersonaDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.IPersonaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class PersonaService {

    @Autowired
    private IPersonaRepository personRepo;

    @Transactional
    public PersonaDto registrarPersona(PersonaDto person){

        if (person.getNombres() == null || person.getNombres().trim().isEmpty() || person.getNombres().length() > 100) {
            throw new IllegalArgumentException("Nombres requeridos y máximo 100 caracteres");
        }
        if (person.getApellidos() == null || person.getApellidos().trim().isEmpty() || person.getApellidos().length() > 100) {
            throw new IllegalArgumentException("Apellidos requeridos y máximo 100 caracteres");
        }
        if (person.getDNI() == null || !person.getDNI().matches("\\d{8}")) {
            throw new IllegalArgumentException("DNI debe tener exactamente 8 dígitos numéricos");
        }
        if (person.getTelefono() == null || !person.getTelefono().matches("\\d{7,15}")) {
            throw new IllegalArgumentException("Teléfono debe tener entre 7 y 15 dígitos numéricos");
        }
        if (person.getEmail() == null || !Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", person.getEmail())) {
            throw new IllegalArgumentException("Email con formato inválido");
        }
        if (person.getFechaNacimiento() == null) {
            throw new IllegalArgumentException("Fecha de nacimiento requerida");
        }
        if (person.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Fecha de nacimiento no puede ser futura");
        }
        
        if (personRepo.existsByDNI(person.getDNI())) {
            throw new IllegalArgumentException("Ya existe una Persona con ese DNI");
        }
        if (personRepo.existsByEmail(person.getEmail())) {
            throw new IllegalArgumentException("Ya existe una persona con ese Email");
        }

        return personRepo.save(person);
    }

    public Optional<PersonaDto> buscarEmail(String email){
        return personRepo.findByEmail(email);
    }

}
