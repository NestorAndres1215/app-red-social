package com.red_social.auth_service.controller;

import com.red_social.auth_service.dto.RegisterRequest;
import com.red_social.auth_service.model.Admin;
import com.red_social.auth_service.model.Moderador;
import com.red_social.auth_service.model.Usuario;
import com.red_social.auth_service.service.AdminService;
import com.red_social.auth_service.service.ModeradorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/moderadores")
@Tag(name = "Moderadores")
public class ModeradorController {

    private final ModeradorService moderadorService;

    @GetMapping("/listar")
    public ResponseEntity<List<Moderador>> listar() {
        return ResponseEntity.ok(moderadorService.listar());
    }

    @GetMapping("/listarId/{codigo}")
    public ResponseEntity<Moderador> listarId(@PathVariable String codigo) {
        return ResponseEntity.ok(moderadorService.listarId(codigo));
    }

    @GetMapping("/listarUsename/{username}")
    public ResponseEntity<List<Moderador>> listarUsername(@PathVariable String username) {
        return ResponseEntity.ok(moderadorService.listarUsername(username));
    }

    @GetMapping("/listarEmail/{email}")
    public ResponseEntity<List<Moderador>> listarEmail(@PathVariable String email) {
        return ResponseEntity.ok(moderadorService.listarEmail(email));
    }

    @GetMapping("/listarTelefono/{telefono}")
    public ResponseEntity<List<Moderador>> listarTelefono(@PathVariable String telefono) {
        return ResponseEntity.ok(moderadorService.listarTelefono(telefono));
    }

    @PostMapping("/registrar")
    public ResponseEntity<Moderador> registrarModerador(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(moderadorService.registrar(request));
    }
}
