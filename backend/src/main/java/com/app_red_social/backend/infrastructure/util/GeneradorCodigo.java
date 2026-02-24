package com.app_red_social.backend.infrastructure.util;

import java.util.UUID;

public final class GeneradorCodigo {

    public static String generarCodigo() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

}