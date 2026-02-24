package com.app_red_social.backend.infrastructure.persistence.repository;

import com.app_red_social.backend.infrastructure.persistence.entity.AdministradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAdministradorRepository extends JpaRepository<AdministradorEntity,String> {
}
