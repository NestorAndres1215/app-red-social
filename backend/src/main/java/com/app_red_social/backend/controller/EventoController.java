package com.app_red_social.backend.controller;

import com.app_red_social.backend.model.Evento;
import com.app_red_social.backend.service.EventoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/evento")
@Tag(name = "Evento")
public class EventoController {

    private final EventoService eventoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Evento>> listarTodos() {
        return ResponseEntity.ok(eventoService.listar());
    }

    @GetMapping("/listar/codigo/{codigo}")
    public ResponseEntity<Evento> obtenerPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(eventoService.listarCodigo(codigo));
    }

    @GetMapping("/listar/usuario/{usuario}")
    public ResponseEntity<List<Evento>> listarPorUsuario(@PathVariable String usuario) {
        return ResponseEntity.ok(eventoService.listarPorUsuarioCreador(usuario));
    }

    @GetMapping("/listar/nombre/{nombre}")
    public ResponseEntity<List<Evento>> listarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(eventoService.listarPorNombre(nombre));
    }

    @GetMapping("/listar/nombre/like/{nombre}")
    public ResponseEntity<List<Evento>> listarPorNombreLike(@PathVariable String nombre) {
        return ResponseEntity.ok(eventoService.listarPorNombreLike(nombre));
    }

    @GetMapping("/fecha/exacta")
    public ResponseEntity<List<Evento>> listarFechaExacta(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(eventoService.listarFechaExacta(fecha));
    }

    @GetMapping("/fecha/mayor")
    public ResponseEntity<List<Evento>> listarFechaMayor(
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha) {
        return ResponseEntity.ok(eventoService.listarFechaMayor(fecha));
    }

    @GetMapping("/fecha/menor")
    public ResponseEntity<List<Evento>> listarFechaMenor(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha) {
        return ResponseEntity.ok(eventoService.listarFechaMenor(fecha));
    }

    @GetMapping("/fecha/entre")
    public ResponseEntity<List<Evento>> listarFechaEntre(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(eventoService.listarFechaEntre(inicio, fin));
    }

}
