package com.Innovacion.Taller.domain.service;

import com.Innovacion.Taller.domain.dto.EspecialidadDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.IEspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadService {

    @Autowired
    private IEspecialidadRepository repo;

    public List<EspecialidadDto> listarTodas() {
        return repo.findAll();
    }

    public Optional<EspecialidadDto> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public EspecialidadDto registrar(EspecialidadDto dto) {
        return repo.save(dto);
    }

}
