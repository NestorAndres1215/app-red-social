package com.app_red_social.backend.controller;

import com.app_red_social.backend.model.CodigoVerificacion;
import com.app_red_social.backend.model.Login;
import com.app_red_social.backend.service.CodigoVerificacionService;
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

    private final CodigoVerificacionService codigoVerificacionService;

    @GetMapping("/listar")
    public ResponseEntity<List<CodigoVerificacion>> listar() {
        return ResponseEntity.ok(codigoVerificacionService.listar());
    }

    @PostMapping("/verificar/correo/{username}")
    public ResponseEntity<CodigoVerificacion> verificarCorreo(@PathVariable String username) {
        return ResponseEntity.ok(codigoVerificacionService.verificacionRecuperacionContrasena(username));
    }

    @PostMapping("/verificar/codigo/{codigo}")
    public ResponseEntity<CodigoVerificacion> verificacionCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(codigoVerificacionService.verificacionCodigo(codigo));
    }

}
