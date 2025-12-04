package com.app_red_social.backend.controller;

import com.app_red_social.backend.model.CodigoVerificacion;
import com.app_red_social.backend.service.CodigoVerificacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
