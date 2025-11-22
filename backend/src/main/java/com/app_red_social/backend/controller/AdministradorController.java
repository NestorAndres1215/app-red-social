package com.app_red_social.backend.controller;

import com.app_red_social.backend.dto.request.RegisterRequest;
import com.app_red_social.backend.model.Administrador;
import com.app_red_social.backend.service.AdministradorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/administrador")
@Tag(name = "Administrador")
public class AdministradorController {

    private final AdministradorService administradorService;

    @GetMapping("/listar")
    public ResponseEntity<List<Administrador>> listar() {
        return ResponseEntity.ok(administradorService.listar());
    }

    @GetMapping("/listar/usuario/codigo/{usuarioCodigo}")
    public ResponseEntity<Administrador> listarUsuarioCodigo(@PathVariable String usuarioCodigo) {
        return ResponseEntity.ok(administradorService.listarAdministradorCodigoUser(usuarioCodigo));
    }

    @GetMapping("/listar/username/{username}")
    public ResponseEntity<Administrador> obtenerPorUsername(@PathVariable String username) {
        return ResponseEntity.ok(administradorService.listarUsername(username));
    }

    @GetMapping("/listar/email/{email}")
    public ResponseEntity<Administrador> obtenerPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(administradorService.listarEmail(email));
    }

    @GetMapping("/listar/telefono/{telefono}")
    public ResponseEntity<Administrador> obtenerPorTelefono(@PathVariable String telefono) {
        return ResponseEntity.ok(administradorService.listarTelefono(telefono));
    }

    @PostMapping("/registrar")
    public ResponseEntity<Administrador> registrarAdministrador(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(administradorService.registrar(request));
    }

}
