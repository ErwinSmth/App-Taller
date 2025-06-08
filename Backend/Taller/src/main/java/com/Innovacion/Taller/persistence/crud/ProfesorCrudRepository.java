package com.Innovacion.Taller.persistence.crud;

import com.Innovacion.Taller.persistence.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD:Taller/src/main/java/com/Innovacion/Taller/persistence/crud/ProfesorCrudRepository.java
import java.util.Optional;

public interface ProfesorCrudRepository extends JpaRepository<Profesor,Long> {

    Optional<Profesor>findByEspecialidades(String especialidades);
=======
import java.util.List;
import java.util.Optional;

public interface ProfesorCrudRepository extends JpaRepository<Profesor, Long> {

    Optional<Profesor> findByUsuarioUserId(Long userId);
    List<Profesor> findByEspecialidadesContaining(String especialidad);

>>>>>>> main:Backend/Taller/src/main/java/com/Innovacion/Taller/persistence/crud/ProfesorCrudRepository.java
}
