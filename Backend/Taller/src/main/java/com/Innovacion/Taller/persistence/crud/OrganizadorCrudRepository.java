package com.Innovacion.Taller.persistence.crud;

import com.Innovacion.Taller.persistence.entity.Organizador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizadorCrudRepository extends JpaRepository<Organizador, Long> {
}
