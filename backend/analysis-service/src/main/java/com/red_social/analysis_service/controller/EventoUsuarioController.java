package com.red_social.analysis_service.controller;

import com.red_social.analysis_service.model.EventoUsuario;
import com.red_social.analysis_service.service.EventoUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/evento-usuario")
@RequiredArgsConstructor
public class EventoUsuarioController {

    private final EventoUsuarioService eventoUsuarioService;

    // Listar todos los usuarios
    @GetMapping("/listar")
    public ResponseEntity<List<EventoUsuario>> listarTodos() {
        return ResponseEntity.ok(eventoUsuarioService.listarTodos());
    }

    // Listar por código
    @GetMapping("/listar/{codigo}")
    public ResponseEntity<EventoUsuario> listarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(eventoUsuarioService.listarPorCodigo(codigo));
    }

    // Listar por username
    @GetMapping("/listar-username/{username}")
    public ResponseEntity<EventoUsuario> listarPorUsername(@PathVariable String username) {
        return ResponseEntity.ok(eventoUsuarioService.listarPorUsername(username));
    }

}
