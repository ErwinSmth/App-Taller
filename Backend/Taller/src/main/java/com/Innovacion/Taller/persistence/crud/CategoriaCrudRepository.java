package com.Innovacion.Taller.persistence.crud;

import com.Innovacion.Taller.persistence.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaCrudRepository extends JpaRepository<Categoria, Long> {



}
