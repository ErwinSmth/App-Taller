package com.Innovacion.Taller.persistence.crud;

import com.Innovacion.Taller.persistence.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministradorCrudRepository extends JpaRepository<Administrador, Long> {
    Optional<Administrador> findByUsuarioUserId(Long userId);
}
