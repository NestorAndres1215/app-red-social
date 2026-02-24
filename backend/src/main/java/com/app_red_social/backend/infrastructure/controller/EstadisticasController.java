package com.app_red_social.backend.infrastructure.controller;


import com.app_red_social.backend.application.dto.estadisticas.EstadisticasResponse;
import com.app_red_social.backend.application.dto.estadisticas.TotalCantidadResponse;
import com.app_red_social.backend.domain.port.usecases.EstadisticasUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estadisticas")
@Tag(name = "Estadísticas")
public class EstadisticasController {

    private final EstadisticasUseCase estadisticasUseCase;

    @GetMapping("/moderador/modulos")
    public ResponseEntity<List<EstadisticasResponse>>listarModeraodrModulos() {
        return ResponseEntity.ok(estadisticasUseCase.estadisticaModerador(1));
    }

    @GetMapping("/moderador/total-moderador")
    public ResponseEntity<List<TotalCantidadResponse>> listarModeradorTotal() {
        return ResponseEntity.ok(estadisticasUseCase.totalModerador(2));
    }

    @GetMapping("/moderador/genero")
    public ResponseEntity<List<EstadisticasResponse>>  listarModeradorGenero() {
        return  ResponseEntity.ok(estadisticasUseCase.estadisticaModerador(3));
    }

    @GetMapping("/moderador/pais")
    public ResponseEntity<List<EstadisticasResponse>>  listarModeradorPais() {
        return  ResponseEntity.ok(estadisticasUseCase.estadisticaModerador(4));
    }

    @GetMapping("/moderador/estado")
    public ResponseEntity<List<EstadisticasResponse>> listarModeradorEstado() {
        return  ResponseEntity.ok(estadisticasUseCase.estadisticaModerador(5));
    }

    @GetMapping("/moderador/fecha/dia")
    public ResponseEntity<List<EstadisticasResponse>> listarModeradorFechaDia() {
        return  ResponseEntity.ok(estadisticasUseCase.estadisticaModerador(6));
    }

    @GetMapping("/moderador/fecha/semana")
    public ResponseEntity<List<EstadisticasResponse>> listarModeradorFechaSemana() {
        return ResponseEntity.ok(estadisticasUseCase.estadisticaModerador(7));
    }

    @GetMapping("/moderador/fecha/mes")
    public ResponseEntity<List<EstadisticasResponse>>  listarModeradorFechaMes() {
        return  ResponseEntity.ok(estadisticasUseCase.estadisticaModerador(8));
    }

}
