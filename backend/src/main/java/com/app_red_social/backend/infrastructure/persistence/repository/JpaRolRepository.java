package com.app_red_social.backend.infrastructure.persistence.repository;

import com.app_red_social.backend.domain.model.Rol;
import com.app_red_social.backend.infrastructure.persistence.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaRolRepository  extends JpaRepository<RolEntity,String> {

    Optional<RolEntity> findByNombre(String nombre);

}
