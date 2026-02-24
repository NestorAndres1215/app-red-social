package com.app_red_social.backend.domain.port.usecases;

import com.app_red_social.backend.domain.model.Menu;
import com.app_red_social.backend.domain.model.Rol;

import java.util.List;
import java.util.Optional;

public interface MenuUseCase {

    List<Menu> listarTodos();

    List<Menu> listarRoles(Rol rol);

    Optional<Menu> listarCodigo(String codigo);

    List<Menu> obtenerMenuPorNivel(int nivel);

    List<Menu> obtenerMenusPorDosRoles(String rolCodigo);
}
