package com.Innovacion.Taller.persistence.crud;

import com.Innovacion.Taller.persistence.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolCrudRepository extends JpaRepository<Rol, Long> {

    Optional<Rol> findByRolName(String rolName);

}
