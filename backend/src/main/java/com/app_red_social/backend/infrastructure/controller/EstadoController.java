package com.app_red_social.backend.infrastructure.controller;

import com.app_red_social.backend.domain.model.EstadoUsuario;
import com.app_red_social.backend.domain.port.usecases.EstadoUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estado-usuario")
@Tag(name = "Estado Usuario")
public class EstadoController {

    private final EstadoUseCase estadoUseCase;

    @GetMapping("/listar")
    public ResponseEntity<List<EstadoUsuario>> listar() {
        return ResponseEntity.ok(estadoUseCase.listarTodos());
    }

    @GetMapping("/listar/codigo/{id}")
    public ResponseEntity<EstadoUsuario> listarCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(estadoUseCase.buscarPorCodigo(codigo));
    }

    @GetMapping("/listar/nombre/{nombre}")
    public ResponseEntity<EstadoUsuario> listarNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(estadoUseCase.buscarPorNombre(nombre));
    }
}
