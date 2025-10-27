package com.red_social.analysis_service.service;

import com.red_social.analysis_service.dto.UserEvent;
import com.red_social.analysis_service.model.EventoUsuario;
import com.red_social.analysis_service.repository.EventoUsuarioRepository;
import com.red_social.analysis_service.utils.SecuenciaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoUsuarioService {

    private final EventoUsuarioRepository eventoUsuarioRepository;

    public String ultimoCodigo() {
        return eventoUsuarioRepository.obtenerCodigo();
    }

    public EventoUsuario registrar(UserEvent userEvent) {

        String ultimoCodigo = ultimoCodigo();
        String nuevoCodigo = SecuenciaUtil.generarSiguienteCodigo(ultimoCodigo);

        EventoUsuario eventoUsuario = EventoUsuario.builder()
                .codigo(nuevoCodigo)
                .username(userEvent.getUsername())
                .nombre(userEvent.getNombre())
                .apellido(userEvent.getApellido())
                .genero(userEvent.getGenero())
                .pais(userEvent.getPais())
                .edad(userEvent.getEdad())
                .rol(userEvent.getRol())
                .proveedor(userEvent.getProveedor())
                .estado(userEvent.getEstado())
                .fechaRegistro(LocalDateTime.now())
                .verificado(userEvent.getVerificado())
                .build();

        return eventoUsuarioRepository.save(eventoUsuario);
    }

    // Listar todos los eventos de usuario
    public List<EventoUsuario> listarTodos() {
        return eventoUsuarioRepository.findAll();
    }

    // Listar por username
    public EventoUsuario listarPorUsername(String username) {
        return eventoUsuarioRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con username: " + username));
    }


    // Listar por código
    public EventoUsuario listarPorCodigo(String codigo) {
        return eventoUsuarioRepository.findById(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con código: " + codigo));
    }


}
