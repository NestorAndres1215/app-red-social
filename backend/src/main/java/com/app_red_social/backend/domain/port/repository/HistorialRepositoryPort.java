package com.app_red_social.backend.domain.port.repository;

import com.app_red_social.backend.application.dto.historial.HistorialResponse;

import com.app_red_social.backend.domain.model.HistorialUsuario;

import java.util.List;
import java.util.Optional;

public interface HistorialRepositoryPort {

    String ultimoCodigo();
    List<HistorialUsuario> listarTodos();
    Optional<HistorialUsuario> buscarPorCodigo(String codigo);
    HistorialUsuario registrar(HistorialUsuario request);
    List<HistorialResponse> listarHistorial(Integer opcion, String username, String estado);
}
