package com.red_social.analysis_service.controller;

import com.red_social.analysis_service.model.EstadisticasGenero;
import com.red_social.analysis_service.service.EstadisticasGeneroService;
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
@RequestMapping("/estadisticas-genero")
@Tag(name = "Estadísticas de Género")
public class EstadisticasGeneroController {

    private final  EstadisticasGeneroService estadisticasGeneroService;

    @GetMapping("/listar")
    public ResponseEntity<List<EstadisticasGenero>> listar() {
        return ResponseEntity.ok(estadisticasGeneroService.listar());
    }

    @GetMapping("/listarCodigo/{codigo}")
    public ResponseEntity<EstadisticasGenero> listarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(estadisticasGeneroService.listarId(codigo));
    }

    @GetMapping("/listarGenero/{genero}")
    public ResponseEntity<EstadisticasGenero> listarPorGenero(@PathVariable String genero) {
        return ResponseEntity.ok(estadisticasGeneroService.listarGenero(genero));
    }

}
