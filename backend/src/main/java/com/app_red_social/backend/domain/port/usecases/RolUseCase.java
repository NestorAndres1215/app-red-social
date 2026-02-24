package com.app_red_social.backend.domain.port.usecases;

import com.app_red_social.backend.domain.model.Rol;

import java.util.List;
import java.util.Optional;

public interface RolUseCase {

    List<Rol> listarTodos();

    Rol buscarPorNombre(String nombre);

    Rol buscarPorCodigo(String codigo);
}
