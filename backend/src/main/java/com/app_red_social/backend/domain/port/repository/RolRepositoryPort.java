package com.app_red_social.backend.domain.port.repository;

import com.app_red_social.backend.domain.model.Rol;
import java.util.List;
import java.util.Optional;

public interface RolRepositoryPort {

    List<Rol> listarTodos();

    Optional<Rol> buscarPorNombre(String nombre);

    Optional<Rol> buscarPorCodigo(String codigo);

}
