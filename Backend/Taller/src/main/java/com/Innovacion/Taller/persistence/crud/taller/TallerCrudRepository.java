package com.Innovacion.Taller.persistence.crud.taller;

import com.Innovacion.Taller.persistence.entity.taller.Taller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TallerCrudRepository extends JpaRepository<Taller, Long> {

    List<Taller> findByIdCategoria(Long idCategoria);

    //Listar por el profesor y organizador
    List<Taller> findByProfesorProfesorId(Long profesorId);
    //Organizador clase, organizadorId atributo de la clase
    List<Taller> findByOrganizadorOrganizadorId(Long organizadorId);

    //Buscar taller por titulo
    List<Taller> findByTituloContainingIgnoreCase (String titulo);

    @Query("SELECT t FROM Taller t WHERE t.categoria.categoriaId = :categoriaId AND " +
            "NOT EXISTS (SELECT i FROM Inscripcion i WHERE i.taller.tallerId = t.tallerId AND i.estado = 'COMPLETADO')")
    List<Taller> findByCategoriaIdAndNoCompletado(@Param("categoriaId") Long categoriaId);

}
