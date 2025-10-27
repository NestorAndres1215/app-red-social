package com.red_social.analysis_service.controller;

import com.red_social.analysis_service.model.EstadisticasRoles;
import com.red_social.analysis_service.service.EstadisticasRolesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/estadisticas-roles")
@Tag(name = "Estadísticas de Roles")
public class EstadisticasRolesController {

    private final EstadisticasRolesService estadisticasRolesService;

    @GetMapping("/listar")
    public ResponseEntity<List<EstadisticasRoles>> listar() {
        return ResponseEntity.ok(estadisticasRolesService.listar());
    }

    @GetMapping("/listarCodigo/{codigo}")
    public ResponseEntity<EstadisticasRoles> listarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(estadisticasRolesService.listarId(codigo));
    }

    @GetMapping("/listarRoles/{roles}")
    public ResponseEntity<EstadisticasRoles> listarPorRoles(@PathVariable String roles) {
        return ResponseEntity.ok(estadisticasRolesService.listarRoles(roles));
    }
}
