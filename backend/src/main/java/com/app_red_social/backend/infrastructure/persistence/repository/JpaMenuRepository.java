package com.app_red_social.backend.infrastructure.persistence.repository;

import com.app_red_social.backend.infrastructure.persistence.entity.MenuEntity;
import com.app_red_social.backend.infrastructure.persistence.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaMenuRepository extends JpaRepository<MenuEntity,String> {

    List<MenuEntity> findByRolCodigo(String codigo);
    List<MenuEntity> findByNombre(RolEntity rol);

    List<MenuEntity> findByNivel(Integer menuNivel);

    @Query("SELECT m FROM MenuEntity  m WHERE m.rol.codigo IN (:codigos)")
    List<MenuEntity> findMenusByRolesCodigos(List<String> codigos);
}
