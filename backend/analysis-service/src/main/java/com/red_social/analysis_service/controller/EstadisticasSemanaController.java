package com.red_social.analysis_service.controller;

import com.red_social.analysis_service.model.EstadisticasSemana;
import com.red_social.analysis_service.service.EstadisticasSemanaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estadisticas-semana")
@Tag(name = "Estadísticas de Semana")
@RequiredArgsConstructor
public class EstadisticasSemanaController {

    private final EstadisticasSemanaService estadisticasSemanaService;

    // Listar todas las semanas
    @GetMapping("/listar")
    public ResponseEntity<List<EstadisticasSemana>> listar() {
        return ResponseEntity.ok(estadisticasSemanaService.listar());
    }

    // Buscar semana por código
    @GetMapping("/listarCodigo/{codigo}")
    public ResponseEntity<EstadisticasSemana> listarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(estadisticasSemanaService.listarPorCodigo(codigo));
    }

    // Buscar semana por número y mes-año
    @GetMapping("/listarNumeroMes/{numeroSemana}/{mesAnio}")
    public ResponseEntity<EstadisticasSemana> listarPorNumeroYMes(
            @PathVariable Integer numeroSemana,
            @PathVariable String mesAnio) {
        return ResponseEntity.ok(estadisticasSemanaService.listarPorNumeroYMes(numeroSemana, mesAnio));
    }

    // Obtener la última semana registrada
    @GetMapping("/ultimaSemana")
    public ResponseEntity<EstadisticasSemana> obtenerUltimaSemana() {
        return ResponseEntity.ok(estadisticasSemanaService.obtenerUltimaSemana());
    }
}
