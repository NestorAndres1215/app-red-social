package com.red_social.auth_service.repository;

import com.red_social.auth_service.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin,String> {

    List<Admin> findByLogin_Username(String username);

    List<Admin> findByLogin_Email(String email);

    List<Admin> findByLogin_Telefono(String telefono);

    @Query(value = "SELECT MAX(ad_codigo) FROM Admin", nativeQuery = true)
    String obtenerCodigo();
}
