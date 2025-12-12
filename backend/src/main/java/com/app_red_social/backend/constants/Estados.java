package com.app_red_social.backend.constants;

public class Estados {

    public static final String ACTIVO = "ACTIVO";                        // Usuario activo, acceso total
    public static final String INACTIVO = "INACTIVO";                    // Usuario inactivo por falta de uso
    public static final String BLOQUEADO = "BLOQUEADO";                  // Bloqueado por infracción
    public static final String SUSPENDIDO = "SUSPENDIDO";                // Suspendido temporalmente
    public static final String INHABILITADO = "INHABILITADO";            // Deshabilitado permanentemente
    public static final String EN_REVISION = "EN_REVISION";              // Cuenta en revisión o auditoría
    public static final String PENDIENTE_VERIFICACION = "PENDIENTE_VERIFICACION";  // Falta confirmar correo o identidad
    public static final String VERIFICADO = "VERIFICADO";                // Cuenta oficialmente verificada
    public static final String REPORTADO = "REPORTADO";                  // Denunciado por otros usuarios
    public static final String RESTRINGIDO = "RESTRINGIDO";              // Con acceso limitado (temporal o parcial)
    public static final String BANEADO = "BANEADO";                      // Expulsado permanentemente
    public static final String ELIMINADO = "ELIMINADO";                  // Eliminado (soft delete)
    public static final String PUBLICO = "PUBLICO";
    public static final String PRIVADO = "PRIVADO";
}
