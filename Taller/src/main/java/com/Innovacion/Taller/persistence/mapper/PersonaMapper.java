package com.Innovacion.Taller.persistence.mapper;

import com.Innovacion.Taller.domain.dto.PersonaDto;
import com.Innovacion.Taller.persistence.entity.Persona;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PersonaMapper {

    //Mapstruck ya mapea automaticamente los campos con los mismos nombres
    PersonaDto toPersonaDto(Persona persona);

    @InheritInverseConfiguration  //El mapeo al reves
    @Mapping(target = "usuario", ignore = true)
    Persona toPersona(PersonaDto personaDto);

}
