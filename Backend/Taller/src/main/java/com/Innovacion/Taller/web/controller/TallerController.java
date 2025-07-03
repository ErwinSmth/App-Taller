package com.Innovacion.Taller.web.controller;

import com.Innovacion.Taller.domain.dto.taller.TallerDto;
import com.Innovacion.Taller.domain.dto.taller.TallerResumenDto;
import com.Innovacion.Taller.domain.service.TallerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/taller")
public class TallerController {

    @Autowired
    private TallerService tallerService;

    //Crear un taller
    @PostMapping("/crear")
    public ResponseEntity<TallerDto> crearTaller(@RequestBody TallerDto tallerDto){
        try {
            TallerDto creado = tallerService.crearTaller(tallerDto);
            return new ResponseEntity<>(creado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    //Obtener taller por su id
    @GetMapping("/{id}")
    public ResponseEntity<TallerDto> getTallerById(@PathVariable Long id){
        Optional<TallerDto> taller = tallerService.getTallerById(id);
        return taller.map(ResponseEntity::ok)
                .orElseGet(() ->  new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Eliminar taller por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTaller(@PathVariable Long id){
        try {
            tallerService.eliminarTaller(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Metodos a ser usados por la parte visual para visualizar talleres
    @GetMapping("/listar")
    public ResponseEntity<List<TallerResumenDto>> listarTalleres() {
        return ResponseEntity.ok(tallerService.listarTalleres());
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<TallerResumenDto>> listarPorCategoria(
            @PathVariable Long categoriaId,
            @RequestParam(required = false) Long userId
    ) {
        return ResponseEntity.ok(tallerService.listarTalleresPorCategoriaExcluyendoProfesor(categoriaId, userId));
    }

    // Listar talleres por profesor
    @GetMapping("/profesor/{profesorId}")
    public ResponseEntity<List<TallerResumenDto>> listarPorProfesor(@PathVariable Long profesorId) {
        return ResponseEntity.ok(tallerService.listarTalleresPorProfesor(profesorId));
    }

    // Listar talleres por organizador
    @GetMapping("/organizador/{organizadorId}")
    public ResponseEntity<List<TallerResumenDto>> listarPorOrganizador(@PathVariable Long organizadorId) {
        return ResponseEntity.ok(tallerService.listarTalleresPorOrganizador(organizadorId));
    }

    // Buscar talleres por título
    @GetMapping("/buscar")
    public ResponseEntity<List<TallerResumenDto>> buscarPorTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(tallerService.buscarTalleresPorTitulo(titulo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TallerDto> editarTaller(@PathVariable Long id, @RequestBody TallerDto tallerDto) {
        try {
            TallerDto actualizado = tallerService.editarTaller(id, tallerDto);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
