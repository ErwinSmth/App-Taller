package com.Innovacion.Taller.persistence.mapper;

import com.Innovacion.Taller.domain.dto.PersonaDto;
import com.Innovacion.Taller.persistence.entity.Persona;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-17T22:10:10-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class PersonaMapperImpl implements PersonaMapper {

    @Override
    public PersonaDto toPersonaDto(Persona persona) {
        if ( persona == null ) {
            return null;
        }

        PersonaDto personaDto = new PersonaDto();

        personaDto.setPersonaId( persona.getPersonaId() );
        personaDto.setNombres( persona.getNombres() );
        personaDto.setApellidos( persona.getApellidos() );
        personaDto.setDNI( persona.getDNI() );
        personaDto.setTelefono( persona.getTelefono() );
        personaDto.setEmail( persona.getEmail() );
        personaDto.setFechaNacimiento( persona.getFechaNacimiento() );
        personaDto.setFechaRegistro( persona.getFechaRegistro() );

        return personaDto;
    }

    @Override
    public Persona toPersona(PersonaDto personaDto) {
        if ( personaDto == null ) {
            return null;
        }

        Persona persona = new Persona();

        persona.setPersonaId( personaDto.getPersonaId() );
        persona.setNombres( personaDto.getNombres() );
        persona.setApellidos( personaDto.getApellidos() );
        persona.setDNI( personaDto.getDNI() );
        persona.setTelefono( personaDto.getTelefono() );
        persona.setEmail( personaDto.getEmail() );
        persona.setFechaNacimiento( personaDto.getFechaNacimiento() );
        persona.setFechaRegistro( personaDto.getFechaRegistro() );

        return persona;
    }
}
