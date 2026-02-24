package com.app_red_social.backend.domain.port.repository;

import com.app_red_social.backend.domain.model.EstadoUsuario;

import java.util.List;
import java.util.Optional;

public interface EstadoRepositoryPort {
    List<EstadoUsuario> listarTodos();

    Optional<EstadoUsuario> buscarPorNombre(String nombre);

    Optional<EstadoUsuario> buscarPorCodigo(String codigo);
}