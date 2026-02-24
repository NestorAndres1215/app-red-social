package com.app_red_social.backend.domain.port.repository;

import com.app_red_social.backend.domain.model.Token;

import java.util.List;

public interface TokenRepositoryPort {

    String ultimoCodigo();

    List<Token> obtenerTokenInactivo();

    Token registrar(Token token);

    void deleteInactiveTokens();

}
