package com.Innovacion.Taller.persistence.mapper.persona;

import com.Innovacion.Taller.domain.dto.persona.PersonaDto;
import com.Innovacion.Taller.persistence.entity.persona.Persona;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonaMapper {

    //Mapstruck ya mapea automaticamente los campos con los mismos nombres
    @Mapping(source = "DNI", target = "dni")
    PersonaDto toPersonaDto(Persona persona);

    @InheritInverseConfiguration  //El mapeo al reves
    @Mapping(source = "dni", target = "DNI")
    @Mapping(target = "usuario", ignore = true)
    Persona toPersona(PersonaDto personaDto);

}
