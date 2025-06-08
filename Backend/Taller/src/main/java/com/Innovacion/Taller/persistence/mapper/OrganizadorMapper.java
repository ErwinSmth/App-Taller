package com.Innovacion.Taller.persistence.mapper;

<<<<<<< HEAD:Taller/src/main/java/com/Innovacion/Taller/persistence/mapper/OrganizadorMapper.java

=======
>>>>>>> main:Backend/Taller/src/main/java/com/Innovacion/Taller/persistence/mapper/OrganizadorMapper.java
import com.Innovacion.Taller.domain.dto.OrganizadorDto;
import com.Innovacion.Taller.persistence.entity.Organizador;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

<<<<<<< HEAD:Taller/src/main/java/com/Innovacion/Taller/persistence/mapper/OrganizadorMapper.java
import java.util.List;

@Mapper(componentModel = "spring",uses = {UsuarioMapper.class})
public interface OrganizadorMapper {

    @Mapping(source = "usuario", target = "usuarioDto" )
    OrganizadorDto toOrganizadorDto(Organizador organizador);

    List <OrganizadorDto> toOrganizadorDtoList(List<Organizador>organizadors);

    @InheritInverseConfiguration
    @Mapping(source = "usuarioDto", target = "usuario")
    Organizador toOrganizador(OrganizadorDto organizadorDto);
    List <Organizador> toOrganizadorList(List<OrganizadorDto>organizadorDtos);
=======
@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public interface OrganizadorMapper {

    @Mapping(source = "usuario", target = "userDto")
    OrganizadorDto toOrganizadorDto(Organizador organizador);

    @InheritInverseConfiguration
    @Mapping(source = "userDto", target = "usuario")
    Organizador toOrganizador(OrganizadorDto organizadorDto);
>>>>>>> main:Backend/Taller/src/main/java/com/Innovacion/Taller/persistence/mapper/OrganizadorMapper.java

}
