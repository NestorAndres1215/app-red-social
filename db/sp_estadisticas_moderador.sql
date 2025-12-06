CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_estadisticas_moderador`(
  IN p_option INT
)
BEGIN
  IF p_option = 1 THEN
	SELECT 
		h.hu_modulo AS modulo_historial,
			COUNT(*) AS total_por_modulo,
			CONCAT(ROUND((COUNT(*) / total.total_registros) * 100, 2), '%') AS porcentaje
		FROM historial_usuario h
		JOIN login l ON h.hu_login = l.lg_codigo
		JOIN rol r ON l.lg_rol = r.rl_codigo
		CROSS JOIN (
			SELECT COUNT(*) AS total_registros
			FROM historial_usuario h2
			JOIN login l2 ON h2.hu_login = l2.lg_codigo
			JOIN rol r2 ON l2.lg_rol = r2.rl_codigo
			WHERE r2.rl_nombre = 'ROLE_MODERADOR'
		) AS total
		WHERE r.rl_nombre = 'ROLE_MODERADOR'
		GROUP BY h.hu_modulo, total.total_registros
		ORDER BY total_por_modulo DESC;
  END IF;
  IF p_option = 2 THEN
	-- CANTIDAD DE MODERADOR
	SELECT COUNT(*) AS total_moderadores
	FROM moderador;
  END IF;
  IF p_option = 3 THEN
  
-- PORCENTAJE DE GENERO

SELECT 
    mod_genero AS genero,
    COUNT(*) AS cantidad,
    ROUND( (COUNT(*) / total.total_registros) * 100 , 2 ) AS porcentaje
FROM moderador,
(
    SELECT COUNT(*) AS total_registros
    FROM moderador
) AS total
GROUP BY mod_genero, total.total_registros;
  END IF;  
  IF p_option = 4 THEN
  
  WITH conteo AS (
    SELECT 
        mod_nacionalidad,
        COUNT(*) AS cantidad
    FROM moderador
    GROUP BY mod_nacionalidad
),
ordenado AS (
    SELECT 
        mod_nacionalidad,
        cantidad,
        ROW_NUMBER() OVER (ORDER BY cantidad DESC) AS rn,
        SUM(cantidad) OVER () AS total_global
    FROM conteo
),
top_5_otros AS (
    SELECT 
        CASE 
            WHEN rn <= 5 THEN mod_nacionalidad
            ELSE 'OTROS'
        END AS pais,
        SUM(cantidad) AS cantidad,
        MAX(total_global) AS total_global
    FROM ordenado
    GROUP BY
        CASE 
            WHEN rn <= 5 THEN mod_nacionalidad
            ELSE 'OTROS'
        END
)
SELECT 
    pais,
    cantidad,
    ROUND((cantidad / total_global) * 100, 2) AS porcentaje
FROM top_5_otros
ORDER BY cantidad DESC;

  END IF;  
  IF p_option = 5 THEN
  WITH moderadores AS (
    SELECT 
        l.lg_codigo,
        l.lg_username,
        l.lg_correo,
        r.rl_nombre AS rol,
        e.st_nombre AS estado
    FROM login l
    INNER JOIN rol r ON l.lg_rol = r.rl_codigo
    INNER JOIN estado_usuario e ON l.lg_estado_usuario = e.st_codigo
    WHERE r.rl_nombre = 'ROLE_MODERADOR'   -- Cambia si tu nombre del rol es otro
),
conteo AS (
    SELECT 
        estado,
        COUNT(*) AS cantidad
    FROM moderadores
    GROUP BY estado
),
totales AS (
    SELECT SUM(cantidad) AS total_moderadores
    FROM conteo
)
SELECT 
    c.estado,
    c.cantidad,
    ROUND((c.cantidad / t.total_moderadores) * 100, 2) AS porcentaje
FROM conteo c
CROSS JOIN totales t
ORDER BY c.cantidad DESC;
  
  END IF;  
  IF p_option = 6 THEN
  WITH datos AS (
    SELECT 
        DATE(l.lg_fecha_registro) AS dia,
        COUNT(*) AS cantidad
    FROM login l
    INNER JOIN rol r ON l.lg_rol = r.rl_codigo
    WHERE r.rl_nombre = 'MODERADOR'
    GROUP BY DATE(l.lg_fecha_registro)
),
total AS (
    SELECT SUM(cantidad) AS total FROM datos
)
SELECT 
    COALESCE(d.dia, 'SIN DATOS') AS dia,
    COALESCE(d.cantidad, 0) AS cantidad,
    COALESCE(ROUND((d.cantidad / t.total) * 100, 2), 0) AS porcentaje
FROM datos d
CROSS JOIN total t

UNION ALL

SELECT 
    'SIN DATOS',
    0 AS cantidad,
    0 AS porcentaje
WHERE NOT EXISTS (SELECT 1 FROM datos);

  END IF;  
  IF p_option = 7 THEN
  WITH datos AS (
    SELECT 
        YEAR(l.lg_fecha_registro) AS anio,
        WEEK(l.lg_fecha_registro) AS semana,
        COUNT(*) AS cantidad
    FROM login l
    INNER JOIN rol r ON l.lg_rol = r.rl_codigo
    WHERE r.rl_nombre = 'MODERADOR'
    GROUP BY YEAR(l.lg_fecha_registro), WEEK(l.lg_fecha_registro)
),
total AS (
    SELECT SUM(cantidad) AS total FROM datos
)
SELECT 
    COALESCE(CONCAT(d.anio, '-W', d.semana), 'SIN DATOS') AS semana,
    COALESCE(d.cantidad, 0) AS cantidad,
    COALESCE(ROUND((d.cantidad / t.total) * 100, 2), 0) AS porcentaje
FROM datos d
CROSS JOIN total t

UNION ALL

SELECT 
    'SIN DATOS',
    0 AS cantidad,
    0 AS porcentaje
WHERE NOT EXISTS (SELECT 1 FROM datos);

  END IF;
    IF p_option = 8 THEN
    WITH datos AS (
    SELECT 
        YEAR(l.lg_fecha_registro) AS anio,
        MONTHNAME(l.lg_fecha_registro) AS mes,
        COUNT(*) AS cantidad
    FROM login l
    INNER JOIN rol r ON l.lg_rol = r.rl_codigo
    WHERE r.rl_nombre = 'MODERADOR'
    GROUP BY YEAR(l.lg_fecha_registro), MONTHNAME(l.lg_fecha_registro)
),
total AS (
    SELECT SUM(cantidad) AS total FROM datos
)
SELECT 
    COALESCE(CONCAT(d.mes, ' ', d.anio), 'SIN DATOS') AS mes,
    COALESCE(d.cantidad, 0) AS cantidad,
    COALESCE(ROUND((d.cantidad / t.total) * 100, 2), 0) AS porcentaje
FROM datos d
CROSS JOIN total t

UNION ALL

SELECT 
    'SIN DATOS',
    0 AS cantidad,
    0 AS porcentaje
WHERE NOT EXISTS (SELECT 1 FROM datos);

  END IF;
  
  
END