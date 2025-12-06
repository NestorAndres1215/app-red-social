CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualAdministrador`(
    IN p_loginCodigo VARCHAR(8)
)
BEGIN
    SELECT 
        a.ad_codigo AS codigoAdministrador,
        a.ad_nombre AS nombre,
        a.ad_apellido AS apellido,
        CONCAT(a.ad_nombre, ' ', a.ad_apellido) AS nombreCompleto,

        a.ad_edad AS edad,
        a.ad_fecha_nacimiento AS fechaNacimiento,
        DATE_FORMAT(a.ad_fecha_nacimiento, '%d/%m/%Y') AS fechaNacimientoFormateada,
        TIMESTAMPDIFF(YEAR, a.ad_fecha_nacimiento, CURDATE()) AS edadCalculada,

        a.ad_genero AS genero,
        a.ad_nacionalidad AS nacionalidad,
        a.ad_perfil AS fotoPerfil,

        l.lg_codigo AS codigoLogin,
        l.lg_correo AS correo,
        l.lg_telefono AS telefono,
        COALESCE(NULLIF(l.lg_username, ''), l.lg_correo) AS username,
        l.lg_rol AS rol,
        l.lg_estado_usuario AS estadoUsuario,
        l.lg_ultimo_login AS ultimoLogin,

        l.lg_fecha_registro AS fechaRegistro

    FROM administrador a
    INNER JOIN login l ON a.us_login = l.lg_codigo
    WHERE l.lg_codigo = p_loginCodigo
    LIMIT 1;
END