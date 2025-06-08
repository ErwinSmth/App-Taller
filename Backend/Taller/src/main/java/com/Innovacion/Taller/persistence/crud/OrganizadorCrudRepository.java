package com.Innovacion.Taller.persistence.crud;

import com.Innovacion.Taller.persistence.entity.Organizador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

<<<<<<< HEAD:Taller/src/main/java/com/Innovacion/Taller/persistence/crud/OrganizadorCrudRepository.java
public interface OrganizadorCrudRepository extends JpaRepository<Organizador,Long> {

    Optional<Organizador>findByRuc(String ruc);

=======
public interface OrganizadorCrudRepository extends JpaRepository<Organizador, Long> {

    Optional<Organizador> findByUsuarioUserId(Long userId);
    Optional<Organizador> findByRuc(String ruc);
>>>>>>> main:Backend/Taller/src/main/java/com/Innovacion/Taller/persistence/crud/OrganizadorCrudRepository.java
}
