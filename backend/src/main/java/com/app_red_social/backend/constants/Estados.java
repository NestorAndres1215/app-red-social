package com.app_red_social.backend.constants;

public class Estados {
    // 🔹 Estados principales
    public static final String ACTIVO = "ACTIVO";                        // Usuario activo, acceso total
    public static final String INACTIVO = "INACTIVO";                    // Usuario inactivo por falta de uso
    public static final String BLOQUEADO = "BLOQUEADO";                  // Bloqueado por infracción
    public static final String SUSPENDIDO = "SUSPENDIDO";                // Suspendido temporalmente
    public static final String INHABILITADO = "INHABILITADO";            // Deshabilitado permanentemente
    public static final String EN_REVISION = "EN_REVISION";              // Cuenta en revisión o auditoría

    //Estados relacionados a verificación y seguridad
    public static final String PENDIENTE_VERIFICACION = "PENDIENTE_VERIFICACION";  // Falta confirmar correo o identidad
    public static final String VERIFICADO = "VERIFICADO";                // Cuenta oficialmente verificada

    //Estados derivados de conducta o moderación
    public static final String REPORTADO = "REPORTADO";                  // Denunciado por otros usuarios
    public static final String RESTRINGIDO = "RESTRINGIDO";              // Con acceso limitado (temporal o parcial)
    public static final String BANEADO = "BANEADO";                      // Expulsado permanentemente

    // 🔹 Estado administrativo
    public static final String ELIMINADO = "ELIMINADO";                  // Eliminado (soft delete)
}
