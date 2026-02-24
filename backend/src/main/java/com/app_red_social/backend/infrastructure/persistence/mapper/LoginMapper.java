package com.app_red_social.backend.infrastructure.persistence.mapper;

import com.app_red_social.backend.domain.model.Login;
import com.app_red_social.backend.infrastructure.persistence.entity.LoginEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginMapper {

    private final RolMapper rolMapper;
    private final EstadoMapper estadoMapper;

    public Login toDomain(LoginEntity entity) {
        if (entity == null) return null;

        return Login.builder()
                .codigo(entity.getCodigo())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .telefono(entity.getTelefono())
                .password(entity.getPassword())
                .rol(rolMapper.toDomain(entity.getRol()))
                .ultimoLogin(entity.getUltimoLogin())
                .estadoUsuario(estadoMapper.toDomain(entity.getEstadoUsuario()))
                .fechaCreacion(entity.getFechaCreacion())
                .build();
    }

    public LoginEntity toEntity(Login login) {
        if (login == null) return null;

        return LoginEntity.builder()
                .codigo(login.getCodigo())
                .username(login.getUsername())
                .email(login.getEmail())
                .telefono(login.getTelefono())
                .password(login.getPassword())
                .rol(rolMapper.toEntity(login.getRol()))
                .ultimoLogin(login.getUltimoLogin())
                .estadoUsuario(estadoMapper.toEntity(login.getEstadoUsuario()))
                .fechaCreacion(login.getFechaCreacion())
                .build();
    }

}
