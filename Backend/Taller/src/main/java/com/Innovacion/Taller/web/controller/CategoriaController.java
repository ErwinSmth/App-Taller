package com.Innovacion.Taller.web.controller;

import com.Innovacion.Taller.domain.dto.taller.CategoriaDto;
import com.Innovacion.Taller.domain.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService cateService;

    @PostMapping("/crear")
    public ResponseEntity<CategoriaDto> crearCategoria(@RequestBody CategoriaDto categoriaDto){
        CategoriaDto creada = cateService.crearCategoria(categoriaDto);
        return ResponseEntity.ok(creada);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CategoriaDto>> listarCategorias() {
        return ResponseEntity.ok(cateService.listarCategorias());
    }

}
