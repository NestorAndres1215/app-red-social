package com.app_red_social.backend.infrastructure.persistence.adapter;

import com.app_red_social.backend.domain.constant.Estados;
import com.app_red_social.backend.domain.model.Token;
import com.app_red_social.backend.domain.port.repository.TokenRepositoryPort;
import com.app_red_social.backend.infrastructure.persistence.entity.TokenEntity;
import com.app_red_social.backend.infrastructure.persistence.mapper.TokenMapper;
import com.app_red_social.backend.infrastructure.persistence.repository.JpaTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TokenRepositoryAdapter implements TokenRepositoryPort {

    private  final JpaTokenRepository tokenRepository;
    private  final TokenMapper mapper;

    @Override
    public String ultimoCodigo() {
        return tokenRepository.obtenerCodigo();
    }

    @Override
    public List<Token> obtenerTokenInactivo() {
        return tokenRepository.findByValido(Estados.INACTIVO)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Token registrar(Token token) {
        TokenEntity entity = mapper.toEntity(token);
        TokenEntity saved = tokenRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteInactiveTokens() {
//        tokenRepository.deleteInactiveTokens();
    }
}
