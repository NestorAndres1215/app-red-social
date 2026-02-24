package com.app_red_social.backend.infrastructure.controller;

import com.app_red_social.backend.application.dto.moderador.ModeradorRequest;
import com.app_red_social.backend.domain.model.Moderador;
import com.app_red_social.backend.domain.port.usecases.ModeradorUserCase;
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

    private final ModeradorUserCase moderadorUserCase;

    @GetMapping("/listar/codigo/{codigo}")
    public ResponseEntity<Moderador> obtenerPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(moderadorUserCase.listarModeradorCodigo(codigo));
    }

    @GetMapping("/listar/username/{username}")
    public ResponseEntity<Moderador> obtenerPorUsername(@PathVariable String username) {
        return ResponseEntity.ok(moderadorUserCase.listarUsername(username));
    }

    @GetMapping("/listar/email/{email}")
    public ResponseEntity<Moderador> obtenerPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(moderadorUserCase.listarEmail(email));
    }

    @GetMapping("/listar/telefono/{telefono}")
    public ResponseEntity<Moderador> obtenerPorTelefono(@PathVariable String telefono) {
        return ResponseEntity.ok(moderadorUserCase.listarTelefono(telefono));
    }

    @PostMapping("/registrar")
    public ResponseEntity<Moderador> registrarAdministrador(@Valid @RequestBody ModeradorRequest request) {
        return ResponseEntity.ok(moderadorUserCase.registrar(request));
    }

    @PutMapping("/actualizar/{codigo}")
    public ResponseEntity<Moderador> actualizar(@PathVariable String codigo,@Valid @RequestBody ModeradorRequest request) {
        return ResponseEntity.ok(moderadorUserCase.actualizar(codigo,request));
    }
}
