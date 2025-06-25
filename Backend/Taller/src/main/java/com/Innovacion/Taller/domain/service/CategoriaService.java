package com.Innovacion.Taller.domain.service;

import com.Innovacion.Taller.domain.dto.taller.CategoriaDto;
import com.Innovacion.Taller.domain.repositoryInterfaces.taller.ICategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private ICategoriaRepository categoriaRepo;

    public CategoriaDto crearCategoria(CategoriaDto categoriaDto){
        return categoriaRepo.save(categoriaDto);
    }

    public List<CategoriaDto> listarCategorias(){
        return categoriaRepo.findAll();
    }

}
