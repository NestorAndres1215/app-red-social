package com.red_social.auth_service.service;

import com.red_social.auth_service.constants.EstadoConstants;
import com.red_social.auth_service.exception.ResourceNotFoundException;
import com.red_social.auth_service.model.Token;
import com.red_social.auth_service.repository.TokenRepository;
import com.red_social.auth_service.util.SecuenciaUtil;
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
                .orElseThrow(() -> new ResourceNotFoundException("TOKEN NO ENCONTRADO"));

        token.setValido(EstadoConstants.INACTIVO);
        tokenRepository.save(token);
    }


    public List<Token> obtenerTokenInactivo() {
        return tokenRepository.findByValido(EstadoConstants.INACTIVO);
    }


    @Scheduled(cron = "0 0 0 ? * SUN")
    public void deleteInactiveTokensWeekly() {
        List<Token> inactiveTokens = obtenerTokenInactivo();
        if (!inactiveTokens.isEmpty()) {
            tokenRepository.deleteAll(inactiveTokens);
        }
    }

    public Token createToken(String jwt) {
        String ultimoCodigo = ultimoCodigo();
        String nuevoCodigo = SecuenciaUtil.generarSiguienteCodigo(ultimoCodigo);

        Token token = Token.builder()
                .codigo(nuevoCodigo)
                .token(jwt)
                .valido(EstadoConstants.ACTIVO)
                .fechaCreacion(LocalDateTime.now())
                .fechaExpiracion(LocalDateTime.now().plusDays(7))
                .build();

        return tokenRepository.save(token);
    }


    public String ultimoCodigo() {
        return tokenRepository.obtenerCodigo();
    }
}
