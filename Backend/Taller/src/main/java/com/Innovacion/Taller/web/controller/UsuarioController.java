package com.Innovacion.Taller.web.controller;

import com.Innovacion.Taller.domain.dto.usuario.PermisoDto;
import com.Innovacion.Taller.domain.dto.usuario.UsuarioDto;
import com.Innovacion.Taller.domain.dto.usuario.UsuarioRegistroDto;
import com.Innovacion.Taller.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

        @Autowired
        private UsuarioService userService;

        @PostMapping("/registrar")
        public ResponseEntity<UsuarioDto> registrar(@RequestBody UsuarioRegistroDto userDto){
            try {
                UsuarioDto user = userService.registrarUsuario(userDto);
                return new ResponseEntity<>(user, HttpStatus.CREATED);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDto> login(@RequestBody UsuarioDto loginRequest) {
        try {
            // Llamar al servicio para validar las credenciales
            Optional<UsuarioDto> usuario = userService.login(loginRequest.getNameUser(), loginRequest.getContraseña());
            return usuario.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> editarUsuario(@PathVariable Long id, @RequestBody UsuarioRegistroDto userDto) {
        try {
            UsuarioDto actualizado = userService.editarUsuario(id, userDto);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/info/{userId}")
    public ResponseEntity<?> obtenerInfoUsuario(@PathVariable Long userId) {
        Optional<UsuarioDto> usuarioOpt = userService.buscarPorId(userId);
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        UsuarioDto usuario = usuarioOpt.get();

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("roles", usuario.getRoles());

        // Obtener permisos por rol usando el servicio
        Map<Long, List<PermisoDto>> permisosPorRol = userService.obtenerPermisosPorRol(usuario.getRoles());
        respuesta.put("permisos", permisosPorRol);

        return ResponseEntity.ok(respuesta);
    }
}

