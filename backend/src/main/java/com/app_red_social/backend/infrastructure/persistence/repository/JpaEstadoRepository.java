package com.app_red_social.backend.infrastructure.persistence.repository;

import com.app_red_social.backend.infrastructure.persistence.entity.EstadoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JpaEstadoRepository extends JpaRepository<EstadoUsuarioEntity,String> {

    Optional<EstadoUsuarioEntity> findByNombre(String nombre);

}
