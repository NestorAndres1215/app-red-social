package com.red_social.analysis_service.service;

import com.red_social.analysis_service.model.EstadisticasRoles;
import com.red_social.analysis_service.repository.EstadisticasRolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadisticasRolesService {

    private final EstadisticasRolesRepository estadisticasRolesRepository;

    public List<EstadisticasRoles> listar() {
        return estadisticasRolesRepository.findAll();
    }

    public EstadisticasRoles listarId(String codigo) {
        return estadisticasRolesRepository.findById(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con código: " + codigo));
    }

    public EstadisticasRoles listarRoles(String roles) {
        return estadisticasRolesRepository.findByRoles(roles)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado: " + roles));
    }
}