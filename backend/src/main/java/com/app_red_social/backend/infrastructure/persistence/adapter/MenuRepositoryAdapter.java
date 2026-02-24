package com.app_red_social.backend.infrastructure.persistence.adapter;

import com.app_red_social.backend.domain.model.Menu;
import com.app_red_social.backend.domain.model.Rol;
import com.app_red_social.backend.domain.port.repository.MenuRepositoryPort;
import com.app_red_social.backend.infrastructure.persistence.entity.RolEntity;
import com.app_red_social.backend.infrastructure.persistence.mapper.MenuMapper;
import com.app_red_social.backend.infrastructure.persistence.repository.JpaMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MenuRepositoryAdapter implements MenuRepositoryPort {

    private  final JpaMenuRepository menuRepository;
    private  final MenuMapper mapper;

    @Override
    public List<Menu> listarTodos() {
        return menuRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }


    @Override
    public List<Menu> listarRoles(Rol rol) {
        return menuRepository.findByRolCodigo(rol.getCodigo())
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Menu> listarCodigo(String codigo) {
        return menuRepository.findById(codigo).map(mapper::toDomain);
    }

    @Override
    public List<Menu> obtenerMenuPorNivel(int nivel) {
        return menuRepository.findByNivel(nivel)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Menu> obtenerMenusPorDosRoles(List<String> codigos ) {
        return menuRepository.findMenusByRolesCodigos(codigos)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
