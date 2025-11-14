package com.app_red_social.backend.controller;

import com.app_red_social.backend.model.EstadoUsuario;
import com.app_red_social.backend.service.EstadoUsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/estado-usuario")
@Tag(name = "Estado Usuario")
public class EstadoUsuarioController {

    private final EstadoUsuarioService estadoUsuarioService;

    @GetMapping("/listar")
    public ResponseEntity<List<EstadoUsuario>> listarEstados() {
        return ResponseEntity.ok(estadoUsuarioService.listar());
    }

    @GetMapping("/listarCodigo/{codigo}")
    public ResponseEntity<EstadoUsuario> listarCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(estadoUsuarioService.listarCodigo(codigo));
    }

    @PostMapping("/registrar")
    public ResponseEntity<EstadoUsuario> registrar(@RequestBody EstadoUsuario estadoUsuario) {
        return ResponseEntity.ok(estadoUsuarioService.registrar(estadoUsuario));
    }

}
