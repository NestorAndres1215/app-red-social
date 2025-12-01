package com.app_red_social.backend.controller;

import com.app_red_social.backend.dto.request.RegisterRequest;
import com.app_red_social.backend.dto.response.AdministradorActualResponse;
import com.app_red_social.backend.model.Administrador;
import com.app_red_social.backend.model.Login;
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

    @GetMapping("/lista/actual/{loginCodigo}")
    public ResponseEntity<AdministradorActualResponse> obtenerAdministradorPorLogin(@PathVariable String loginCodigo) {
        return ResponseEntity.ok(administradorService.obtenerPorLogin(loginCodigo));
    }

    @PutMapping("/activar/{codigoAdmin}")
    public ResponseEntity<Login> activar(@PathVariable String codigoAdmin) {
        return ResponseEntity.ok(administradorService.activar(codigoAdmin));
    }

    @PutMapping("/inactivar/{codigoAdmin}")
    public ResponseEntity<Login> inactivar(@PathVariable String codigoAdmin) {
        return ResponseEntity.ok(administradorService.inactivar(codigoAdmin));
    }

    @PutMapping("/suspender/{codigoAdmin}")
    public ResponseEntity<Login> suspender(@PathVariable String codigoAdmin) {
        return ResponseEntity.ok(administradorService.suspender(codigoAdmin));
    }

    @PutMapping("/bloquear/{codigoAdmin}")
    public ResponseEntity<Login> bloquear(@PathVariable String codigoAdmin) {
        return ResponseEntity.ok(administradorService.bloquear(codigoAdmin));
    }

    @PutMapping("/eliminar/{codigoAdmin}")
    public ResponseEntity<Login> eliminar(@PathVariable String codigoAdmin) {
        return ResponseEntity.ok(administradorService.eliminar(codigoAdmin));
    }
}
