package com.Innovacion.Taller.persistence.repository.taller;

import com.Innovacion.Taller.domain.dto.taller.TallerDto;
import com.Innovacion.Taller.domain.dto.taller.TallerResumenDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.taller.ITallerRepository;
import com.Innovacion.Taller.persistence.crud.taller.TallerCrudRepository;
import com.Innovacion.Taller.persistence.entity.taller.Taller;
import com.Innovacion.Taller.persistence.mapper.taller.TallerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TallerRepositoryImpl implements ITallerRepository {

    @Autowired
    private TallerCrudRepository tallerCrud;

    @Autowired
    private TallerMapper mapper;

    @Override
    public TallerDto save(TallerDto taller) {
        Taller tall = mapper.toTaller(taller);

        if (tall.getImagenes() != null){
            tall.getImagenes().forEach(img -> img.setTaller(tall));
        }

        return mapper.toTallerDto(tallerCrud.save(tall));
    }

    @Override
    public Optional<TallerDto> findById(Long id) {
        return tallerCrud.findById(id).map(mapper::toTallerDto);
    }

    @Override
    public void deteleById(Long id) {
        tallerCrud.deleteById(id);
    }

    @Override
    public List<TallerResumenDto> findAll() {
        return tallerCrud.findAll().stream()
                .map(mapper::toTallerResumenDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TallerResumenDto> findByCategoriaId(Long CategoriaId) {
        return tallerCrud.findByIdCategoria(CategoriaId).stream()
                .map(mapper::toTallerResumenDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TallerResumenDto> findProfesorId(Long profesorId) {
        return tallerCrud.findByProfesorProfesorId(profesorId).stream()
                .map(mapper::toTallerResumenDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TallerResumenDto> findByOrganizadorId(Long organizadorId) {
        return tallerCrud.findByOrganizadorOrganizadorId(organizadorId).stream()
                .map(mapper::toTallerResumenDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TallerResumenDto> findByTituloContaining(String titulo) {
        return tallerCrud.findByTituloContainingIgnoreCase(titulo).stream()
                .map(mapper::toTallerResumenDto)
                .collect(Collectors.toList());
    }

    // ...existing code...
    @Override
    public List<TallerResumenDto> findByCategoriaIdExcluyendoProfesor(Long categoriaId, Long profesorId) {
        return tallerCrud.findByIdCategoria(categoriaId).stream()
                .map(mapper::toTallerResumenDto)
                .filter(dto -> profesorId == null || !profesorId.equals(dto.getProfesorId()))
                .collect(Collectors.toList());
    }
// ...existing code...
}
