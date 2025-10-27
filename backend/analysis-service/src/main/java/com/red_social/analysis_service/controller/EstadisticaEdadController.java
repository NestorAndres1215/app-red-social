package com.red_social.analysis_service.controller;

import com.red_social.analysis_service.model.EstadisticasEdad;
import com.red_social.analysis_service.model.EstadisticasGenero;
import com.red_social.analysis_service.service.EstadisticasEdadService;
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
@RequestMapping("/estadisticas-edad")
@Tag(name = "Estadísticas de Edad")
public class EstadisticaEdadController {

    private final EstadisticasEdadService estadisticasEdadService;

    @GetMapping("/listar")
    public ResponseEntity<List<EstadisticasEdad>> listar() {
        return ResponseEntity.ok(estadisticasEdadService.listar());
    }

    @GetMapping("/listarCodigo/{codigo}")
    public ResponseEntity<EstadisticasEdad> listarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(estadisticasEdadService.listarId(codigo));
    }

    @GetMapping("/listarEdad/{edad}")
    public ResponseEntity<EstadisticasEdad> listarPorGenero(@PathVariable String edad) {
        return ResponseEntity.ok(estadisticasEdadService.listarEdad(edad));
    }


}
