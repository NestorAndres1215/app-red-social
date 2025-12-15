package com.app_red_social.backend.repository;

import com.app_red_social.backend.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface EventoRepository extends JpaRepository<Evento, String> {

    @Query(value = "SELECT MAX(ev_codigo) FROM evento", nativeQuery = true)
    String obtenerUltimoCodigo();

    List<Evento> findByUsuarioCreador(String usuarioCreador);

    List<Evento> findByNombre(String nombre);

    List<Evento> findByNombreContainingIgnoreCase(String nombre);

    List<Evento> findByEstado(String estado);

    List<Evento> findByFechaInicio(LocalDateTime fechaInicio);

    List<Evento> findByFechaInicioAfter(LocalDateTime fecha);

    List<Evento> findByFechaInicioBefore(LocalDateTime fecha);

    List<Evento> findByFechaInicioBetween(LocalDateTime inicio, LocalDateTime fin);
}
