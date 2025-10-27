package com.red_social.analysis_service.service;

import com.red_social.analysis_service.model.EstadisticasProveedor;
import com.red_social.analysis_service.repository.EstadisticasProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadisticasProveedorService {

    private final EstadisticasProveedorRepository estadisticasProveedorRepository;

    public List<EstadisticasProveedor> listar() {
        return estadisticasProveedorRepository.findAll();
    }

    public EstadisticasProveedor listarId(String codigo) {
        return estadisticasProveedorRepository.findById(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado con código: " + codigo));
    }

    public EstadisticasProveedor listarProveedor(String proveedor) {
        return estadisticasProveedorRepository.findByProveedor(proveedor)
                .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado: " + proveedor));
    }
}
