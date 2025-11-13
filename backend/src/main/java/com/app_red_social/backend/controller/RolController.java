package com.app_red_social.backend.controller;


import com.app_red_social.backend.model.Rol;
import com.app_red_social.backend.service.RolService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rol")
@Tag(name = "Rol", description = "Controlador para la gestión de roles")
public class RolController {

    private final RolService rolService;

    @GetMapping("/listar")
    public ResponseEntity<List<Rol>> listar() {
        return ResponseEntity.ok(rolService.listar());
    }

    @GetMapping("/listarCodigo/{id}")
    public ResponseEntity<Rol> listarCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(rolService.listarCodigo(codigo));
    }

    @PostMapping("/registrar")
    public ResponseEntity<Rol> registrar(@RequestBody Rol rol) {
        return ResponseEntity.ok(rolService.registrar(rol));
    }
}
