package com.app_red_social.backend.infrastructure.util;

public final  class Validacion {

    public static String normalizarNombre(String nombre) {
        if (nombre == null) return null;

        String result = nombre.toUpperCase();

        result = result.replace("Ñ", "N");
        result = java.text.Normalizer.normalize(result, java.text.Normalizer.Form.NFD);
        result = result.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        result = result.replaceAll("[^A-Z0-9]", "_");

        return result;
    }
}
