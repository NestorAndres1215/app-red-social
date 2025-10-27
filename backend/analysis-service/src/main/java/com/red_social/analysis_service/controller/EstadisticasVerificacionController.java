package com.red_social.analysis_service.controller;

import com.red_social.analysis_service.model.EstadisticasVerificacion;
import com.red_social.analysis_service.service.EstadisticasVerificacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estadisticas-verificacion")
@Tag(name = "Estadísticas de Verificación")
@RequiredArgsConstructor
public class EstadisticasVerificacionController {

    private final EstadisticasVerificacionService estadisticasVerificacionService;

    // Listar todas las verificaciones
    @GetMapping("/listar")
    public ResponseEntity<List<EstadisticasVerificacion>> listar() {
        return ResponseEntity.ok(estadisticasVerificacionService.listar());
    }

    // Buscar por código
    @GetMapping("/listarCodigo/{codigo}")
    public ResponseEntity<EstadisticasVerificacion> listarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(estadisticasVerificacionService.listarPorCodigo(codigo));
    }

    // Buscar por estado
    @GetMapping("/listarEstado/{estado}")
    public ResponseEntity<EstadisticasVerificacion> listarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(estadisticasVerificacionService.listarPorEstado(estado));
    }
}
