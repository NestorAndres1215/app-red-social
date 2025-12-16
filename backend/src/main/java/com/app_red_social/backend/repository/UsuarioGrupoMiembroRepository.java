package com.app_red_social.backend.repository;

import com.app_red_social.backend.model.UsuarioGrupoMiembro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UsuarioGrupoMiembroRepository  extends JpaRepository<UsuarioGrupoMiembro, String> {
    List<UsuarioGrupoMiembro> findByFechaRegistro(LocalDateTime fechaRegistro);
    List<UsuarioGrupoMiembro> findByUsuarioGrupo_Nombre(String nombre);
}
