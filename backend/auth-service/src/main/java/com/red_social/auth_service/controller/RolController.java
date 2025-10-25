package com.red_social.auth_service.controller;

import com.red_social.auth_service.model.Rol;
import com.red_social.auth_service.service.RolService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rol")
@Tag(name = "Rol")
public class RolController {

    private final RolService rolService;

    @GetMapping("/listar")
    public ResponseEntity<List<Rol>> listarRoles() {
        return ResponseEntity.ok(rolService.listarRoles());
    }

    @GetMapping("/listarId/{id}")
    public ResponseEntity<Rol> listarId(@PathVariable String id) {
        return ResponseEntity.ok(rolService.listarId(id));
    }

    @PostMapping("/registrar")
    public ResponseEntity<Rol> registrar(@RequestBody Rol rol) {
        return ResponseEntity.ok(rolService.registrar(rol));
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Rol> actualizar(@RequestBody Rol rol) {
        return ResponseEntity.ok(rolService.actualizar(rol));
    }
}
