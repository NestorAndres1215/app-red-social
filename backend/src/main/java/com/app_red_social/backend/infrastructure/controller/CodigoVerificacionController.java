package com.app_red_social.backend.infrastructure.controller;

import com.app_red_social.backend.domain.model.CodigoVerificacion;
import com.app_red_social.backend.domain.port.usecases.CodigoVerificacionUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/codigo-verificacion")
@Tag(name = "Codigo de Verificacion")
public class CodigoVerificacionController {

    private  final CodigoVerificacionUseCase codigoVerificacionUseCase;

    @GetMapping("/listar")
    public ResponseEntity<List<CodigoVerificacion>> listar() {
        return ResponseEntity.ok(codigoVerificacionUseCase.listar());
    }

    @PostMapping("/verificar/correo/{username}")
    public ResponseEntity<CodigoVerificacion> verificarCorreo(@PathVariable String username) {
        return ResponseEntity.ok(codigoVerificacionUseCase.verificacionRecuperacionContrasena(username));
    }

    @PostMapping("/verificar/codigo/{codigo}")
    public ResponseEntity<CodigoVerificacion> verificacionCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(codigoVerificacionUseCase.verificacionCodigo(codigo));
    }

}
