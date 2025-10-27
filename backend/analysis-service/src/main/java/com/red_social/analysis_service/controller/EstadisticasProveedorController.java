package com.red_social.analysis_service.controller;

import com.red_social.analysis_service.model.EstadisticasProveedor;
import com.red_social.analysis_service.service.EstadisticasProveedorService;
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
@RequestMapping("/estadisticas-proveedor")
@Tag(name = "Estadísticas de Proveedor")
public class EstadisticasProveedorController {

    private final EstadisticasProveedorService estadisticasProveedorService;

    @GetMapping("/listar")
    public ResponseEntity<List<EstadisticasProveedor>> listar() {
        return ResponseEntity.ok(estadisticasProveedorService.listar());
    }

    @GetMapping("/listarCodigo/{codigo}")
    public ResponseEntity<EstadisticasProveedor> listarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(estadisticasProveedorService.listarId(codigo));
    }

    @GetMapping("/listarProveedor/{proveedor}")
    public ResponseEntity<EstadisticasProveedor> listarPorProveedor(@PathVariable String proveedor) {
        return ResponseEntity.ok(estadisticasProveedorService.listarProveedor(proveedor));
    }

}
