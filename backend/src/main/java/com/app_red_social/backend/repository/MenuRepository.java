package com.app_red_social.backend.repository;

import com.app_red_social.backend.model.Menu;
import com.app_red_social.backend.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, String> {

    List<Menu> findByRol(Rol rol);

    List<Menu> findByNombre(Rol rol);

    List<Menu> findByNivel(Integer menuNivel);

    @Query("SELECT m FROM Menu m WHERE m.rol.codigo IN (:codigos)")
    List<Menu> findMenusByRolesCodigos(List<String> codigos);


}