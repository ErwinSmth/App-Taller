package com.Innovacion.Taller.persistence.crud.taller;

import com.Innovacion.Taller.persistence.entity.taller.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaCrudRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNombre(String nombre);

}
