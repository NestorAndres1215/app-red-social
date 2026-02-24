package com.app_red_social.backend.infrastructure.persistence.repository;

import com.app_red_social.backend.infrastructure.persistence.entity.EventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaEventoRepository extends JpaRepository<EventoEntity, String> {

    @Query(value = "SELECT MAX(ev_codigo) FROM evento", nativeQuery = true)
    String obtenerUltimoCodigo();

    List<EventoEntity> findByUsuarioCreador(String usuarioCreador);

    List<EventoEntity> findByNombre(String nombre);

    List<EventoEntity> findByNombreContainingIgnoreCase(String nombre);

    List<EventoEntity> findByEstado(String estado);

    List<EventoEntity> findByFechaInicio(LocalDateTime fechaInicio);

    List<EventoEntity> findByFechaInicioAfter(LocalDateTime fecha);

    List<EventoEntity> findByFechaInicioBefore(LocalDateTime fecha);

    List<EventoEntity> findByFechaInicioBetween(LocalDateTime inicio, LocalDateTime fin);
}
