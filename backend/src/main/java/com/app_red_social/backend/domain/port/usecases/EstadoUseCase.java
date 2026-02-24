package com.app_red_social.backend.domain.port.usecases;

import com.app_red_social.backend.domain.model.EstadoUsuario;
import java.util.List;
import java.util.Optional;

public interface EstadoUseCase {

    List<EstadoUsuario> listarTodos();

    EstadoUsuario buscarPorNombre(String nombre);

    EstadoUsuario buscarPorCodigo(String codigo);
}
