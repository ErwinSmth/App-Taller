package com.Innovacion.Taller.persistence.crud;

import com.Innovacion.Taller.persistence.entity.Organizador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface OrganizadorCrudRepository extends JpaRepository<Organizador,Long> {

    Optional<Organizador>findByRuc(String ruc);
    Optional<Organizador> findByUsuarioUserId(Long userId);

}
