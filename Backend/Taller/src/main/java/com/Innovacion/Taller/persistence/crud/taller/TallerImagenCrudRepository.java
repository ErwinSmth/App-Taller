package com.Innovacion.Taller.persistence.crud.taller;


import com.Innovacion.Taller.persistence.entity.taller.TallerImagen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TallerImagenCrudRepository extends JpaRepository<TallerImagen, Long> {

    List<TallerImagen> findByTaller_TallerId(Long tallerId);

}
