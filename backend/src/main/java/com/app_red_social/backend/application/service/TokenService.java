package com.app_red_social.backend.application.service;

import com.app_red_social.backend.domain.constant.Estados;
import com.app_red_social.backend.domain.model.Token;
import com.app_red_social.backend.domain.port.repository.TokenRepositoryPort;
import com.app_red_social.backend.domain.port.usecases.TokenUseCase;
import com.app_red_social.backend.infrastructure.util.Secuencia;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService implements TokenUseCase {

     private  final TokenRepositoryPort tokenRepositoryPort;

    @Override
    public List<Token> obtenerTokenInactivo() {
        return tokenRepositoryPort.obtenerTokenInactivo();
    }

    @Override
    public Token registrar(String token) {
        String nuevoCodigo = Secuencia.generarSiguienteCodigo(tokenRepositoryPort.ultimoCodigo());
        Token tokenEntity = Token.builder()
                .codigo(nuevoCodigo)
                .token(token)
                .valido(Estados.ACTIVO)
                .fechaRegistro(LocalDateTime.now())
                .fechaExpiracion(LocalDateTime.now().plusDays(7))
                .build();
        return tokenRepositoryPort.registrar(tokenEntity);
    }


    @Scheduled(cron = "0 0 0 ? * SUN")
    public void deleteInactiveTokensWeekly() {
        tokenRepositoryPort.deleteInactiveTokens();
    }
}
