package com.Innovacion.Taller.persistence.crud.usuario;

import com.Innovacion.Taller.persistence.entity.usuario.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermisoCrudRepository extends JpaRepository<Permiso, Long> {

    List<Permiso> findByRoles_RolIdIn(List<Long> rolIds);
}
