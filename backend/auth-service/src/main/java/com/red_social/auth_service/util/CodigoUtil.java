package com.red_social.auth_service.util;


import java.util.UUID;

public class CodigoUtil {

    public static String generarCodigo() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

}