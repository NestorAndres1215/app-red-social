package com.red_social.auth_service.repository;

import com.red_social.auth_service.dto.response.UsuarioDetalleResponse;
import com.red_social.auth_service.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    List<Usuario> findByLogin_Username(String username);

    List<Usuario> findByLogin_Email(String email);

    List<Usuario> findByLogin_Telefono(String telefono);

    @Query(value = "SELECT MAX(us_codigo) FROM Usuario", nativeQuery = true)
    String obtenerCodigo();


    @Query(
            value = "SELECT " +
                    "u.us_codigo, u.us_nombre, u.us_apellido, u.us_edad, u.us_fecha_nacimiento, " +
                    "u.us_genero, u.us_nacionalidad, u.us_presentacion, u.us_photo_url, " +
                    "u.us_banner, u.us_perfil, l.lg_correo, l.lg_telefono, l.lg_username, " +
                    "r.tr_nombre AS rol_nombre, l.lg_ultimo_login, e.st_nombre AS estado_nombre " +
                    "FROM usuario u " +
                    "INNER JOIN login l ON u.us_codigo = l.lg_codigo " +
                    "INNER JOIN estados_usuario e ON l.lg_estado_usuario = e.st_codigo " +
                    "INNER JOIN rol r ON l.lg_rol = r.tr_codigo " +
                    "ORDER BY l.lg_fecha_creacion DESC",
            nativeQuery = true
    )
    List<Object[]> listarUsuariosConDetalleNative();
}
