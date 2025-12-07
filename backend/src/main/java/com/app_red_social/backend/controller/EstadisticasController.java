package com.app_red_social.backend.controller;

import com.app_red_social.backend.dto.response.EstadisticasResponse;

import com.app_red_social.backend.dto.response.TotalCantidadResponse;
import com.app_red_social.backend.service.EstadisticasService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estadisticas")
@Tag(name = "Estadísticas")
public class EstadisticasController {

    private final EstadisticasService estadisticasService;

    @GetMapping("/moderador/modulos")
    public List<EstadisticasResponse> listarModeraodrModulos() {
        return estadisticasService.estadisticaModerador(1);
    }

    @GetMapping("/moderador/total-moderador")
    public List<TotalCantidadResponse> listarModeradorTotal() {
        return estadisticasService.totalModerador(2);
    }

    @GetMapping("/moderador/genero")
    public List<EstadisticasResponse> listarModeradorGenero() {
        return estadisticasService.estadisticaModerador(3);
    }

    @GetMapping("/moderador/pais")
    public List<EstadisticasResponse> listarModeradorPais() {
        return estadisticasService.estadisticaModerador(4);
    }

    @GetMapping("/moderador/estado")
    public List<EstadisticasResponse> listarModeradorEstado() {
        return estadisticasService.estadisticaModerador(5);
    }

    @GetMapping("/moderador/fecha/dia")
    public List<EstadisticasResponse> listarModeradorFechaDia() {
        return estadisticasService.estadisticaModerador(6);
    }

    @GetMapping("/moderador/fecha/semana")
    public List<EstadisticasResponse> listarModeradorFechaSemana() {
        return estadisticasService.estadisticaModerador(7);
    }

    @GetMapping("/moderador/fecha/mes")
    public List<EstadisticasResponse> listarModeradorFechaMes() {
        return estadisticasService.estadisticaModerador(8);
    }
}
