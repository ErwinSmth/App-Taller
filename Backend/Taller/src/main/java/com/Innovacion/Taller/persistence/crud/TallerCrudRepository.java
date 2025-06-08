package com.Innovacion.Taller.persistence.crud;

import com.Innovacion.Taller.persistence.entity.Taller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TallerCrudRepository extends JpaRepository<Taller, Long> {

    List<Taller> findByIdCategoria(Long idCategoria);

    //Listar por el profesor y organizador
    List<Taller> findByProfesorProfesorId(Long profesorId);
    //Organizador clase, organizadorId atributo de la clase
    List<Taller> findByOrganizadorOrganizadorId(Long organizadorId);

    //Buscar taller por titulo
    List<Taller> findByTituloContainingIgnoreCase (String titulo);

}
