package com.Innovacion.Taller.persistence.repository;

import com.Innovacion.Taller.domain.dto.TallerDto;
import com.Innovacion.Taller.domain.dto.TallerResumenDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.ITallerRepository;
import com.Innovacion.Taller.persistence.crud.TallerCrudRepository;
import com.Innovacion.Taller.persistence.entity.Taller;
import com.Innovacion.Taller.persistence.mapper.TallerMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TallerRepositoryImpl implements ITallerRepository {

    @Autowired
    private TallerCrudRepository tallerCrud;

    @Autowired
    private TallerMapper mapper;

    @Override
    public TallerDto save(TallerDto taller) {
        Taller tall = mapper.toTaller(taller);
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
}
