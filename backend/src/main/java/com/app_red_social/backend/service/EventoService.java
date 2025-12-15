package com.app_red_social.backend.service;

import com.app_red_social.backend.constants.messages.NotFoundMessages;
import com.app_red_social.backend.exception.ResourceNotFoundException;
import com.app_red_social.backend.model.Evento;
import com.app_red_social.backend.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;

    public String ultimoCodigo() {
        return eventoRepository.obtenerUltimoCodigo();
    }

    public List<Evento> listar() {
        return eventoRepository.findAll();
    }

    public Evento listarCodigo(String codigo) {
        return eventoRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(NotFoundMessages.CODIGO_NO_ENCONTRADO));
    }

    public List<Evento> listarPorUsuarioCreador(String usuario) {
        return eventoRepository.findByUsuarioCreador(usuario);
    }

    public List<Evento> listarPorNombre(String nombre) {
        return eventoRepository.findByNombre(nombre);
    }

    public List<Evento> listarPorNombreLike(String nombre) {
        return eventoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Evento> listarPorEstado(String estado) {
        return eventoRepository.findByEstado(estado);
    }

    public List<Evento> listarFechaExacta(LocalDate fecha) {
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(23, 59, 59);
        return eventoRepository.findByFechaInicio(inicio);
    }

    public List<Evento> listarFechaMayor(LocalDateTime fecha) {
        return eventoRepository.findByFechaInicioAfter(fecha);
    }

    public List<Evento> listarFechaMenor(LocalDateTime fecha) {
        return eventoRepository.findByFechaInicioBefore(fecha);
    }

    public List<Evento> listarFechaEntre(LocalDateTime inicio, LocalDateTime fin) {
        return eventoRepository.findByFechaInicioBetween(inicio, fin);
    }

}
