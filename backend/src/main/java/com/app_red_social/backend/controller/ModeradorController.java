package com.app_red_social.backend.controller;

import com.app_red_social.backend.dto.request.RegisterRequest;
import com.app_red_social.backend.model.Administrador;
import com.app_red_social.backend.model.Login;
import com.app_red_social.backend.model.Moderador;
import com.app_red_social.backend.service.ModeradorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/moderador")
@Tag(name = "Moderador")
public class ModeradorController {

    private final ModeradorService moderadorService;

    @GetMapping("/listar/codigo/{codigo}")
    public ResponseEntity<Moderador> obtenerPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(moderadorService.listarModeradorCodigo(codigo));
    }

    @GetMapping("/listar/username/{username}")
    public ResponseEntity<Moderador> obtenerPorUsername(@PathVariable String username) {
        return ResponseEntity.ok(moderadorService.listarUsername(username));
    }

    @GetMapping("/listar/email/{email}")
    public ResponseEntity<Moderador> obtenerPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(moderadorService.listarEmail(email));
    }

    @GetMapping("/listar/telefono/{telefono}")
    public ResponseEntity<Moderador> obtenerPorTelefono(@PathVariable String telefono) {
        return ResponseEntity.ok(moderadorService.listarTelefono(telefono));
    }

    @PostMapping("/registrar")
    public ResponseEntity<Moderador> registrarAdministrador(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(moderadorService.registrar(request));
    }

    @PutMapping("/activar/{codigoAdmin}")
    public ResponseEntity<Login> activar(@PathVariable String codigoAdmin) {
        return ResponseEntity.ok(moderadorService.activar(codigoAdmin));
    }

    @PutMapping("/inactivar/{codigoAdmin}")
    public ResponseEntity<Login> inactivar(@PathVariable String codigoAdmin) {
        return ResponseEntity.ok(moderadorService.inactivar(codigoAdmin));
    }

    @PutMapping("/suspender/{codigoAdmin}")
    public ResponseEntity<Login> suspender(@PathVariable String codigoAdmin) {
        return ResponseEntity.ok(moderadorService.suspender(codigoAdmin));
    }

    @PutMapping("/bloquear/{codigoAdmin}")
    public ResponseEntity<Login> bloquear(@PathVariable String codigoAdmin) {
        return ResponseEntity.ok(moderadorService.bloquear(codigoAdmin));
    }

    @PutMapping("/eliminar/{codigoAdmin}")
    public ResponseEntity<Login> eliminar(@PathVariable String codigoAdmin) {
        return ResponseEntity.ok(moderadorService.eliminar(codigoAdmin));
    }
}
