package com.Innovacion.Taller.domain.service;

import com.Innovacion.Taller.domain.dto.persona.AdministradorDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.persona.IAdministradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministradorService {

    @Autowired
    private IAdministradorRepository adminRepo;

    public Optional<AdministradorDto> buscarPorId(Long id){
        if (id == null) throw new IllegalArgumentException("Id Invalido");
        return adminRepo.findById(id);
    }

    public Optional<AdministradorDto> buscarPorUsuarioId(Long id){
        if (id == null) throw new IllegalArgumentException("Id de usuario invalido");
        return adminRepo.findByUsuarioId(id);
    }

    @Transactional
    public void eliminarAdmin(Long id){
        if (id == null) throw new IllegalArgumentException("Id invalido");
        Optional<AdministradorDto> adminDto = adminRepo.findById(id);
        if (adminDto.isEmpty()){
            throw new IllegalArgumentException("El administrador no existe");
        }
        //Metodo para solo desactivar este administrador, no eliminarlo del sistema
    }
}
