package com.red_social.auth_service.constants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthConstants {

    public static final String LOCAL = "LOCAL";
    public static final String GOOGLE = "GOOGLE";
    public static final String TOKEN_INVALIDO = "TOKEN INVÁLIDO O EXPIRADO";
    public static final String TOKEN_NO_VALIDO_PARA_USUARIO = "TOKEN NO VÁLIDO PARA EL USUARIO";
    public static final String USUARIO_NO_VALIDO = "USUARIO NO ENCONTRADO";
    public static final String USUARIO_NO_AUTORIZADO = "USUARIO NO AUTORIZADO";
    public static final String ERROR_LOGIN = "ERROR AL INICIAR SESION";
    public static final String USER_PASS_INCORRECTO = "USUARIO O CONTRASEÑA INCORRECTOS";
    public static final String ERROR_VALIDANDO_TOKEN = "ERROR VALIDAR EL TOKEN JWT";
    public static final String CERRAR_SESION = "CIERRE SESION EXITOSO";

}
