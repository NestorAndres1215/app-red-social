package com.red_social.analysis_service.controller;

import com.red_social.analysis_service.model.EstadisticasPais;
import com.red_social.analysis_service.service.EstadisticasPaisService;
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
@RequestMapping("/estadisticas-pais")
@Tag(name = "Estadísticas de País")
public class EstadisticasPaisController {

    private final EstadisticasPaisService estadisticasPaisService;

    @GetMapping("/listar")
    public ResponseEntity<List<EstadisticasPais>> listar() {
        return ResponseEntity.ok(estadisticasPaisService.listar());
    }

    @GetMapping("/listarCodigo/{codigo}")
    public ResponseEntity<EstadisticasPais> listarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(estadisticasPaisService.listarId(codigo));
    }

    @GetMapping("/listarPais/{pais}")
    public ResponseEntity<EstadisticasPais> listarPorPais(@PathVariable String pais) {
        return ResponseEntity.ok(estadisticasPaisService.listarPais(pais));
    }

}