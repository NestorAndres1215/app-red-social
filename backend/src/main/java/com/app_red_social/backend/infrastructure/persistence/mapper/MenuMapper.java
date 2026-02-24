package com.app_red_social.backend.infrastructure.persistence.mapper;

import com.app_red_social.backend.domain.model.Menu;
import com.app_red_social.backend.infrastructure.persistence.entity.MenuEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MenuMapper {

    private final RolMapper rolMapper;

    public Menu toDomain(MenuEntity entity) {
        if (entity == null) return null;

        return Menu.builder()
                .codigo(entity.getCodigo())
                .nivel(entity.getNivel())
                .nombre(entity.getNombre())
                .tipo(entity.getTipo())
                .icono(entity.getIcono())
                .categoria(entity.getCategoria())
                .menuRuta(entity.getMenuRuta())
                .rol(rolMapper.toDomain(entity.getRol()))
                .build();
    }

    public MenuEntity toEntity(Menu menu) {
        if (menu == null) return null;

        return MenuEntity.builder()
                .codigo(menu.getCodigo())
                .nivel(menu.getNivel())
                .nombre(menu.getNombre())
                .tipo(menu.getTipo())
                .icono(menu.getIcono())
                .categoria(menu.getCategoria())
                .menuRuta(menu.getMenuRuta())
                .rol(rolMapper.toEntity(menu.getRol()))
                .build();
    }
}