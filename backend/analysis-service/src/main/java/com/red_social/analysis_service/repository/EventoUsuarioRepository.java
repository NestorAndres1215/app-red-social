package com.red_social.analysis_service.repository;

import com.red_social.analysis_service.model.EventoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventoUsuarioRepository extends JpaRepository<EventoUsuario, Long> {
    List<EventoUsuario> findByFechaRegistroBetween(LocalDateTime inicio, LocalDateTime fin);


    @Query(value = "SELECT MAX(eu_codigo) FROM Evento_Usuario", nativeQuery = true)
    String obtenerCodigo();
}