package com.Innovacion.Taller.web.controller;

import com.Innovacion.Taller.domain.dto.EspecialidadDto;
import com.Innovacion.Taller.domain.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/especialidad")
public class EspecialidadController {

    @Autowired
    private EspecialidadService service;

    @GetMapping("/listar")
    public ResponseEntity<List<EspecialidadDto>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

}
