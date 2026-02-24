package com.app_red_social.backend.domain.port.usecases;

import com.app_red_social.backend.domain.model.Login;

import java.util.Optional;

public interface LoginUseCase {

    Login actualizar(String codigo, String username, String email, String telefono, String password);

    Login registrar(String codigo, String username, String email, String telefono, String password, String rol);

    Optional<Login> buscarPorCodigo(String codigo);

    Login inactivar(String codigo);

    Login suspender(String codigo);

    Login activar(String codigo);

    Login bloquear(String codigo);

    Login eliminado(String codigo);

    Login inhabilitado(String codigo);

    Login ultimoLogueo(String username);

    Login cambiarEstado(String codigo, String nombreEstado);

}
