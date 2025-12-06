CREATE DEFINER=`root`@`localhost` PROCEDURE `Sp_UsuarioActual`(
    IN opcion VARCHAR(8),
    IN p_codigo VARCHAR(50)
)
BEGIN
    -- Opción 1: Datos completos
    IF opcion = '1' THEN
        
        SELECT 
            u.us_codigo AS codigoUsuario,
            u.us_nombre AS nombre,
            u.us_apellido AS apellido,
            u.us_edad AS edad,
            u.us_fecha_nacimiento AS fechaNacimiento,
            u.us_genero AS genero,
            u.us_nacionalidad AS nacionalidad,
            u.us_presentacion AS presentacion,
            u.us_photo_url AS photoUrl,
            u.us_banner AS banner,
            u.us_perfil AS perfil,
            l.lg_correo AS correo,
            l.lg_telefono AS telefono,
            COALESCE(NULLIF(l.lg_username, ''), l.lg_correo) AS username,
            l.lg_rol AS rol,
            l.lg_ultimo_login AS ultimoLogin,
            l.lg_fecha_creacion AS fechaCreacion,
            l.lg_codigo AS codigoLogin,
            e.st_nombre AS estadoNombre,
            e.st_descripcion AS estadoDescripcion
        FROM usuario u
        INNER JOIN login l ON u.us_login = l.lg_codigo
        INNER JOIN estado_usuario e ON l.lg_estado_usuario = e.st_codigo
        WHERE u.us_codigo = p_codigo
        LIMIT 1;

    -- Opción 2: Datos básicos    
    ELSEIF opcion = '2' THEN
        
        SELECT 
            u.us_codigo AS codigoUsuario,
            u.us_nombre AS nombre,
            u.us_apellido AS apellido,
            l.lg_correo AS correo,
            l.lg_rol AS rol
        FROM usuario u
        INNER JOIN login l ON u.us_login = l.lg_codigo
        WHERE u.us_codigo = p_codigo
        LIMIT 1;

    -- Opción 3: Estado y último login    
    ELSEIF opcion = '3' THEN
        
        SELECT 
            l.lg_ultimo_login AS ultimoLogin,
            e.st_nombre AS estadoNombre,
            e.st_descripcion AS estadoDescripcion
        FROM usuario u
        INNER JOIN login l ON u.us_login = l.lg_codigo
        INNER JOIN estado_usuario e ON l.lg_estado_usuario = e.st_codigo
        WHERE u.us_codigo = p_codigo
        LIMIT 1;

    ELSE
        SELECT 'Opción no válida' AS mensaje;
    END IF;

END