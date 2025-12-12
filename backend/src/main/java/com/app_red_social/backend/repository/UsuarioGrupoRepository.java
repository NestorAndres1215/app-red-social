package com.app_red_social.backend.repository;

import com.app_red_social.backend.model.UsuarioGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioGrupoRepository  extends JpaRepository<UsuarioGrupo,String> {
    @Query(value = "SELECT MAX(ug_codigo) FROM Usuario_Grupo", nativeQuery = true)
    String obtenerCodigo();

    List<UsuarioGrupo> findByEstado(String estado);
    List<UsuarioGrupo> findByPrivacidadAndEstado(String privacidad, String estado);

}
