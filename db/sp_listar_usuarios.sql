CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_usuarios`(
    IN p_option INT,
    IN p_username VARCHAR(250),
    IN p_estado VARCHAR(50)
)
BEGIN

    -- ADMINISTRADOR
    IF p_option = 1 THEN


SELECT 
    a.ad_codigo AS codigo,
    a.ad_nombre AS nombre,
    a.ad_apellido AS apellido,
    a.ad_edad AS edad,
    a.ad_fecha_nacimiento AS fecha_nacimiento,
    a.ad_genero AS genero,
    a.ad_nacionalidad AS nacionalidad,
    COALESCE(a.ad_perfil, '') AS perfil,

    l.lg_username AS username,
    COALESCE(l.lg_correo, '') AS correo,
    COALESCE(l.lg_telefono, '') AS telefono,
    COALESCE(l.lg_fecha_registro, '') AS fecha_registro,
    COALESCE(l.lg_ultimo_login, '') AS ultimo_login,

    COALESCE(e.st_nombre, '') AS estado
FROM administrador a
LEFT JOIN login l ON a.us_login = l.lg_codigo
LEFT JOIN estado_usuario e ON l.lg_estado_usuario = e.st_codigo
WHERE (p_username IS NULL OR l.lg_username <> p_username)
  AND (p_estado IS NULL OR LOWER(e.st_nombre) = LOWER(p_estado));

END IF;

    -- MODERADOR
    IF p_option = 2 THEN
        SELECT 
            m.mod_codigo AS codigo,
            m.mod_nombre AS nombre,
            m.mod_apellido AS apellido,
            m.mod_edad AS edad,
            m.mod_fecha_nacimiento AS fecha_nacimiento,
            m.mod_genero AS genero,
            m.mod_nacionalidad AS nacionalidad,
            m.mod_perfil AS perfil,

            l.lg_username AS username,
            l.lg_correo AS correo,
            l.lg_telefono AS telefono,
            l.lg_fecha_registro AS fecha_registro,
            l.lg_ultimo_login AS ultimo_login,

            e.st_nombre AS estado
        FROM moderador m
        JOIN login l ON m.us_login = l.lg_codigo
        JOIN estado_usuario e ON l.lg_estado_usuario = e.st_codigo
        WHERE (p_username IS NULL OR l.lg_username <> p_username)
          AND (p_estado IS NULL OR e.st_nombre = p_estado)
        ORDER BY m.mod_nombre ASC;
    END IF;

    -- USUARIO
    IF p_option = 3 THEN
        SELECT 
            u.us_codigo AS codigo,
            u.us_nombre AS nombre,
            u.us_apellido AS apellido,
            u.us_edad AS edad,
            u.us_fecha_nacimiento AS fecha_nacimiento,
            u.us_genero AS genero,
            u.us_nacionalidad AS nacionalidad,
            u.us_ciudad AS ciudad,
            u.us_pais AS pais,
            u.us_perfil AS perfil,
            u.us_banner AS banner,
            u.us_photo_url AS foto,
            u.us_presentacion AS presentacion,
            u.us_cuenta_privada AS cuenta_privada,
            u.us_publicaciones AS publicaciones,
            u.us_seguidores AS seguidores,
            u.us_seguidos AS seguidos,
            u.us_verificado AS verificado,

            l.lg_username AS username,
            l.lg_correo AS correo,
            l.lg_telefono AS telefono,
            l.lg_rol AS rol,
            l.lg_fecha_registro AS fecha_registro,
            l.lg_ultimo_login AS ultimo_login,

            e.st_nombre AS estado
        FROM usuario u
        JOIN login l ON u.us_login = l.lg_codigo
        JOIN estado_usuario e ON l.lg_estado_usuario = e.st_codigo
        WHERE (p_username IS NULL OR l.lg_username <> p_username)
          AND (p_estado IS NULL OR e.st_nombre = p_estado)
        ORDER BY u.us_nombre ASC;
    END IF;

END