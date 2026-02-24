package com.app_red_social.backend.infrastructure.controller;

import com.app_red_social.backend.application.dto.historial.HistorialResponse;
import com.app_red_social.backend.application.dto.historial.HistorialUsuarioRequest;
import com.app_red_social.backend.domain.model.HistorialUsuario;
import com.app_red_social.backend.domain.port.usecases.HistorialUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/historial-usuario")
@Tag(name = "Historial Usuario")
public class HistorialController {

    private final HistorialUseCase historialUseCase;

    @GetMapping("/listar")
    public ResponseEntity<List<HistorialUsuario>> listar() {
        return ResponseEntity.ok(historialUseCase.listarTodos());
    }

    @GetMapping("/listar/codigo/{codigo}")
    public ResponseEntity<Optional<HistorialUsuario>> listarCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(historialUseCase.buscarPorCodigo(codigo));
    }

    @GetMapping("/listar/historial/{username}/{estado}")
    public List<HistorialResponse> listarHistorial(@PathVariable String username, @PathVariable String estado) {
        return historialUseCase.listarHistorial(1, username, estado);
    }

    @GetMapping("/listar/historial/moderador")
    public List<HistorialResponse> listarHistorial() {
        return historialUseCase.listarHistorial(2, "", "");
    }

    @PostMapping("/registrar")
    public ResponseEntity<HistorialUsuario> registrar(@RequestBody HistorialUsuarioRequest historialUsuarioRequest) {
        return ResponseEntity.ok(historialUseCase.registrar(historialUsuarioRequest));
    }

    @PutMapping("/inactivar/estado/{codigo}")
    public ResponseEntity<HistorialUsuario> inactivar(@PathVariable String codigo) {
        return ResponseEntity.ok(historialUseCase.inactivar(codigo));
    }


}
