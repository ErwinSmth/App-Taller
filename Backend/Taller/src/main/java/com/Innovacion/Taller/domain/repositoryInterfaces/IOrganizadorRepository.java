package com.Innovacion.Taller.domain.repositoryInterfaces;

import com.Innovacion.Taller.domain.dto.OrganizadorDto;

<<<<<<< HEAD:Taller/src/main/java/com/Innovacion/Taller/domain/repositoryInterfaces/IOrganizadorRepository.java
=======
import java.util.List;
>>>>>>> main:Backend/Taller/src/main/java/com/Innovacion/Taller/domain/repositoryInterfaces/IOrganizadorRepository.java
import java.util.Optional;

public interface IOrganizadorRepository {

<<<<<<< HEAD:Taller/src/main/java/com/Innovacion/Taller/domain/repositoryInterfaces/IOrganizadorRepository.java
    OrganizadorDto save(OrganizadorDto organizadorDto);
    Optional <OrganizadorDto> findByRuc (String ruc);
=======
    OrganizadorDto save(OrganizadorDto organizador);
    Optional<OrganizadorDto> findById(Long id);
    Optional<OrganizadorDto> findByUsuarioId(Long userId);
    Optional<OrganizadorDto> findByRuc(String ruc);

>>>>>>> main:Backend/Taller/src/main/java/com/Innovacion/Taller/domain/repositoryInterfaces/IOrganizadorRepository.java
}
