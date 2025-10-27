package com.red_social.analysis_service.controller;

import com.red_social.analysis_service.model.EstadisticasEdad;
import com.red_social.analysis_service.model.EstadisticasEstado;
import com.red_social.analysis_service.service.EstadisticasEdadService;
import com.red_social.analysis_service.service.EstadisticasEstadoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estadisticas-estado")
@Tag(name = "Estadísticas de Estado")
public class EstadisticasEstadoController {


    private final EstadisticasEstadoService estadisticasEstadoService;

    @GetMapping("/listar")
    public ResponseEntity<List<EstadisticasEstado>> listar() {
        return ResponseEntity.ok(estadisticasEstadoService.listar());
    }

    @GetMapping("/listarCodigo/{codigo}")
    public ResponseEntity<EstadisticasEstado> listarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(estadisticasEstadoService.listarId(codigo));
    }

    @GetMapping("/listarEstado/{estado}")
    public ResponseEntity<EstadisticasEstado> listarPorGenero(@PathVariable String estado) {
        return ResponseEntity.ok(estadisticasEstadoService.listarEstado(estado));
    }

}
