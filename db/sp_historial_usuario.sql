CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_historial_usuario`(
  IN p_option INT,
    IN p_username VARCHAR(250),
    IN p_estado VARCHAR(50)
)
BEGIN
   IF p_option = 1 THEN
    SELECT 
        h.hu_codigo AS codigoHistorial,
        DATE(h.hu_fecha_registro) AS fecha_historial,
        TIME(h.hu_fecha_registro) AS hora_historial,

        CASE 
            WHEN p_username IS NULL OR p_username = '' 
                THEN l.lg_correo
            ELSE 
                l.lg_username
        END AS usuario_historial,

        h.hu_estado AS estado_historial,
        h.hu_titulo AS titulo_historial,
        h.hu_modulo AS modulo_historial,
        h.hu_detalle AS detalle_historial
    FROM historial_usuario h
    JOIN login l ON h.hu_login = l.lg_codigo
    WHERE 
        (
            p_username IS NULL 
            OR p_username = '' 
            OR l.lg_username = p_username
        )
        AND 
        (
            p_estado IS NULL 
            OR p_estado = '' 
            OR h.hu_estado = p_estado
        )
    ORDER BY h.hu_fecha_registro DESC;
    END IF;

    IF p_option = 2 THEN
      SELECT 
            h.hu_codigo AS codigoHistorial,
            DATE(h.hu_fecha_registro) AS fecha_historial,
            TIME(h.hu_fecha_registro) AS hora_historial,
            l.lg_username AS usuario_historial,
            h.hu_estado AS estado_historial,
            h.hu_titulo AS titulo_historial,
            h.hu_modulo AS modulo_historial,
            h.hu_detalle AS detalle_historial
        FROM historial_usuario h
        JOIN login l ON h.hu_login = l.lg_codigo
        JOIN rol r ON l.lg_rol = r.rl_codigo
        WHERE r.rl_nombre = 'ROLE_MODERADOR'
        ORDER BY h.hu_fecha_registro DESC;
	END IF;
END