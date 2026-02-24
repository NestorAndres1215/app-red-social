package com.app_red_social.backend.infrastructure.controller;

import com.app_red_social.backend.domain.model.Rol;
import com.app_red_social.backend.domain.port.usecases.RolUseCase;
import com.app_red_social.backend.infrastructure.persistence.entity.RolEntity;
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
@RequestMapping("/rol")
@Tag(name = "Rol")
public class RolController {

    private final RolUseCase rolUseCase;

    @GetMapping("/listar")
    public ResponseEntity<List<Rol>> listar() {
        return ResponseEntity.ok(rolUseCase.listarTodos());
    }

    @GetMapping("/listar/codigo/{codigo}")
    public ResponseEntity<Rol> listarCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(rolUseCase.buscarPorCodigo(codigo));
    }

    @GetMapping("/listar/nombre/{nombre}")
    public ResponseEntity<Rol> listarNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(rolUseCase.buscarPorNombre(nombre));
    }

}
