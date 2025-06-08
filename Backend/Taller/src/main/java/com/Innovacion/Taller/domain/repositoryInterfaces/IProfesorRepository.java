package com.Innovacion.Taller.domain.repositoryInterfaces;

import com.Innovacion.Taller.domain.dto.ProfesorDto;

<<<<<<< HEAD:Taller/src/main/java/com/Innovacion/Taller/domain/repositoryInterfaces/IProfesorRepository.java
=======
import java.util.List;
>>>>>>> main:Backend/Taller/src/main/java/com/Innovacion/Taller/domain/repositoryInterfaces/IProfesorRepository.java
import java.util.Optional;

public interface IProfesorRepository {

<<<<<<< HEAD:Taller/src/main/java/com/Innovacion/Taller/domain/repositoryInterfaces/IProfesorRepository.java
    ProfesorDto save (ProfesorDto profesorDto);
    Optional <ProfesorDto> findByEspecialidades (String especialidades);
=======
    ProfesorDto save(ProfesorDto profesor);
    Optional<ProfesorDto> findById(Long id);
    Optional<ProfesorDto> findByUsuarioId(Long userId);
    List<ProfesorDto> findByEspecialidad(String especialidad);

>>>>>>> main:Backend/Taller/src/main/java/com/Innovacion/Taller/domain/repositoryInterfaces/IProfesorRepository.java
}
