package com.app_red_social.backend.controller;

import com.app_red_social.backend.dto.request.HistorialUsuarioRequest;
import com.app_red_social.backend.dto.response.HistorialResponse;
import com.app_red_social.backend.model.HistorialUsuario;
import com.app_red_social.backend.service.HistorialUsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/historial-usuario")
@Tag(name = "Historial Usuario")
public class HistorialUsuarioController {

    private final HistorialUsuarioService historialUsuarioService;

    @GetMapping("/listar")
    public ResponseEntity<List<HistorialUsuario>> listar() {
        return ResponseEntity.ok(historialUsuarioService.listar());
    }

    @GetMapping("/listar/codigo/{codigo}")
    public ResponseEntity<HistorialUsuario> listarCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(historialUsuarioService.listarCodigo(codigo));
    }

    @GetMapping("/listar/historial/{username}/{estado}")
    public List<HistorialResponse> listarHistorial(@PathVariable String username, @PathVariable String estado) {
        return historialUsuarioService.listarHistorial(1, username, estado);
    }

    @GetMapping("/listar/historial/moderador")
    public List<HistorialResponse> listarHistorial() {
        return historialUsuarioService.listarHistorial(2, "", "");
    }

    @PostMapping("/registrar")
    public ResponseEntity<HistorialUsuario> registrar(@RequestBody HistorialUsuarioRequest historialUsuarioRequest) {
        return ResponseEntity.ok(historialUsuarioService.registrar(historialUsuarioRequest));
    }

    @DeleteMapping("/inactivar/estado/{codigo}")
    public ResponseEntity<HistorialUsuario> inactivar(@PathVariable String codigo) {
        return ResponseEntity.ok(historialUsuarioService.inactivar(codigo));
    }

}
