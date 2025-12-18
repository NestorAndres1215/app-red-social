package com.app_red_social.backend.repository;

import com.app_red_social.backend.model.UsuarioGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface UsuarioGrupoRepository extends JpaRepository<UsuarioGrupo, String> {

    @Query(value = "SELECT MAX(ug_codigo) FROM Usuario_Grupo", nativeQuery = true)
    String obtenerCodigo();

    List<UsuarioGrupo> findByEstado(String estado);

    List<UsuarioGrupo> findByPrivacidadAndEstado(String privacidad, String estado);

    List<UsuarioGrupo> findByCreador(String creador);

    List<UsuarioGrupo> findByPrivacidad(String privacidad);

    List<UsuarioGrupo> findByNombre(String nombre);

    List<UsuarioGrupo> findByFechaRegistro(LocalDateTime fechaRegistro);
}
