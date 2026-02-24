package com.app_red_social.backend.application.service;

import com.app_red_social.backend.domain.model.Menu;
import com.app_red_social.backend.domain.model.Rol;
import com.app_red_social.backend.domain.port.repository.MenuRepositoryPort;
import com.app_red_social.backend.domain.port.repository.RolRepositoryPort;
import com.app_red_social.backend.domain.port.usecases.MenuUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class MenuService implements MenuUseCase {

private  final MenuRepositoryPort menuRepositoryPort;
private final RolRepositoryPort rolRepositoryPort;

    @Override
    public List<Menu> listarTodos() {
        return menuRepositoryPort.listarTodos();
    }

    @Override
    public List<Menu> listarRoles(Rol rol) {
        return menuRepositoryPort.listarRoles(rol);
    }

    @Override
    public Optional<Menu> listarCodigo(String codigo) {
        return menuRepositoryPort.listarCodigo(codigo);
    }

    @Override
    public List<Menu> obtenerMenuPorNivel(int nivel) {
        return menuRepositoryPort.obtenerMenuPorNivel(nivel);
    }

    @Override
    public List<Menu> obtenerMenusPorDosRoles(String rolCodigo) {
        List<String> codigos = List.of(rolCodigo);
        return menuRepositoryPort.obtenerMenusPorDosRoles(codigos);
    }
}
