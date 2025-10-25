package com.red_social.auth_service.constants;

public class ErrorGlobalConstants {

    // Constructor privado para evitar instanciación
    private ErrorGlobalConstants() {}

    // 🟢 2xx - Éxito
    public static final String OK = "OPERACIÓN EXITOSA";
    public static final String CREADO = "RECURSO CREADO EXITOSAMENTE";

    // 🔵 4xx - Errores del cliente
    public static final String SOLICITUD_INVALIDA = "SOLICITUD INVÁLIDA";  // 400
    public static final String NO_AUTORIZADO = "NO AUTORIZADO";             // 401
    public static final String PROHIBIDO = "PROHIBIDO";                     // 403
    public static final String NO_ENCONTRADO = "NO ENCONTRADO";             // 404
    public static final String CONFLICTO = "CONFLICTO";                     // 409
    public static final String REQUERIMIENTO_NO_ACEPTABLE = "NO ACEPTABLE"; // 406
    public static final String REQUERIMIENTO_INVALIDO = "DATOS INVÁLIDOS";  // 422

    // 🔴 5xx - Errores del servidor
    public static final String ERROR_INTERNO = "ERROR INTERNO DEL SERVIDOR"; // 500
    public static final String SERVICIO_NO_DISPONIBLE = "SERVICIO NO DISPONIBLE"; // 503
    public static final String PUERTA_DE_ENLACE_ERRONEA = "ERROR DE COMUNICACIÓN EXTERNA"; // 502
    public static final String TIEMPO_DE_ESPERA_AGOTADO = "TIEMPO DE ESPERA AGOTADO"; // 504

    // 🌐 Errores de integración o externos
    public static final String ERROR_INTEGRACION_EXTERNA = "ERROR EN SERVICIO EXTERNO";
    public static final String ERROR_API_GOOGLE = "ERROR DE COMUNICACIÓN CON GOOGLE";
    public static final String ERROR_API_EXTERNA = "ERROR AL CONECTAR CON API EXTERNA";

    // 🧩 Otros errores comunes
    public static final String ERROR_VALIDACION = "ERROR DE VALIDACIÓN DE DATOS";
    public static final String ERROR_AUTENTICACION = "ERROR DE AUTENTICACIÓN";
    public static final String ERROR_AUTORIZACION = "ERROR DE AUTORIZACIÓN";
    public static final String ERROR_PROCESO = "ERROR EN EL PROCESO DE EJECUCIÓN";
}
