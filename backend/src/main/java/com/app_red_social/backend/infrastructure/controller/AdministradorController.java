package com.app_red_social.backend.infrastructure.controller;

import com.app_red_social.backend.application.dto.administrador.AdministradorActualResponse;
import com.app_red_social.backend.application.dto.administrador.AdministradorRequest;
import com.app_red_social.backend.domain.model.Administrador;
import com.app_red_social.backend.domain.port.usecases.AdministradorUseCase;
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

    private final AdministradorUseCase administradorUseCase;

    @GetMapping("/listar")
    public ResponseEntity<List<Administrador>> listar() {
        return ResponseEntity.ok(administradorUseCase.listar());
    }

    @GetMapping("/listar/usuario/codigo/{codigo}")
    public ResponseEntity<Administrador> listarCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(administradorUseCase.listarAdministradorCodigo(codigo));
    }

    @GetMapping("/listar/username/{username}")
    public ResponseEntity<Administrador> obtenerPorUsername(@PathVariable String username) {
        return ResponseEntity.ok(administradorUseCase.listarUsername(username));
    }

    @GetMapping("/listar/email/{email}")
    public ResponseEntity<Administrador> obtenerPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(administradorUseCase.listarEmail(email));
    }

    @GetMapping("/listar/telefono/{telefono}")
    public ResponseEntity<Administrador> obtenerPorTelefono(@PathVariable String telefono) {
        return ResponseEntity.ok(administradorUseCase.listarTelefono(telefono));
    }

    @PostMapping("/registrar")
    public ResponseEntity<Administrador> registrar(@Valid @RequestBody AdministradorRequest request) {
        return ResponseEntity.ok(administradorUseCase.registrar(request));
    }

    @PutMapping("/actualizar/{codigo}")
    public ResponseEntity<Administrador> actualizar(@PathVariable String codigo,@Valid @RequestBody AdministradorRequest request) {
        return ResponseEntity.ok(administradorUseCase.actualizar(codigo,request));
    }

    @GetMapping("/lista/actual/{loginCodigo}")
    public ResponseEntity<List<AdministradorActualResponse>> obtenerAdministradorPorLogin(@PathVariable String loginCodigo) {
        return ResponseEntity.ok(administradorUseCase.obtenerPorLogin(loginCodigo));
    }



}
