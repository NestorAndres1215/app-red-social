package com.app_red_social.backend.service;


import com.app_red_social.backend.constants.Estados;
import com.app_red_social.backend.constants.messages.GlobalErrorMessages;
import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.model.Token;
import com.app_red_social.backend.repository.TokenRepository;
import com.app_red_social.backend.util.Secuencia;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;
    private final LoginService loginService;

    public void invalidarToken(String jwt) {
        Token token = tokenRepository.findByToken(jwt)
                .orElseThrow(() -> new ResourceNotFoundException(GlobalErrorMessages.TOKEN_NO_ENCONTRADO));

        token.setValido(Estados.INACTIVO);
        tokenRepository.save(token);
    }


    public List<Token> obtenerTokenInactivo() {
        return tokenRepository.findByValido(Estados.INACTIVO);
    }


    @Scheduled(cron = "0 0 0 ? * SUN")
    public void deleteInactiveTokensWeekly() {
        List<Token> inactiveTokens = obtenerTokenInactivo();
        if (!inactiveTokens.isEmpty()) {
            tokenRepository.deleteAll(inactiveTokens);
        }
    }

    public Token createToken(String jwt) {

        final String nuevoCodigo = Secuencia.generarSiguienteCodigo(ultimoCodigo());

        Token token = Token.builder()
                .codigo(nuevoCodigo)
                .token(jwt)
                .valido(Estados.ACTIVO)
                .fechaCreacion(LocalDateTime.now())
                .fechaExpiracion(LocalDateTime.now().plusDays(7))
                .build();

        return tokenRepository.save(token);
    }


    public String ultimoCodigo() {
        return tokenRepository.obtenerCodigo();
    }
}