package com.app_red_social.backend.infrastructure.persistence.mapper;

import com.app_red_social.backend.domain.model.Token;
import com.app_red_social.backend.domain.model.Usuario;
import com.app_red_social.backend.infrastructure.persistence.entity.TokenEntity;
import com.app_red_social.backend.infrastructure.persistence.entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenMapper {

    private  final LoginMapper loginMapper;

    public Token toDomain(TokenEntity entity) {
        if (entity == null) return null;

        return Token.builder()
                .codigo(entity.getCodigo())
                .token(entity.getToken())
                .valido(entity.getValido())
                .login(loginMapper.toDomain(entity.getLogin()))
                .build();
    }

    public TokenEntity toEntity(Token token) {
        if (token == null) return null;

        return TokenEntity.builder()
                .codigo(token.getCodigo())
                .token(token.getToken())
                .valido(token.getValido())
                .login(loginMapper.toEntity(token.getLogin()))
                .build();
    }
}
