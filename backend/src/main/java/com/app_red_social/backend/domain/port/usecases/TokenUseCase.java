package com.app_red_social.backend.domain.port.usecases;

import com.app_red_social.backend.domain.model.Token;

import java.util.List;

public interface TokenUseCase {

    List<Token> obtenerTokenInactivo();

    Token registrar(String token);
}
