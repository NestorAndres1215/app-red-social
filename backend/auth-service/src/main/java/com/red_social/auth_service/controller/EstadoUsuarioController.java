package com.red_social.auth_service.controller;

import com.red_social.auth_service.model.EstadoUsuario;
import com.red_social.auth_service.service.EstadoUsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
        return ResponseEntity.ok(estadoUsuarioService.listarEstadoUsuario());
    }

    @GetMapping("/listarId/{id}")
    public ResponseEntity<EstadoUsuario> listarId(@PathVariable String id) {
        return ResponseEntity.ok(estadoUsuarioService.listarPorCodigo(id));
    }

    @PostMapping("/registrar")
    public ResponseEntity<EstadoUsuario> crearRol(@RequestBody EstadoUsuario estadoUsuario)  {
        return ResponseEntity.ok(estadoUsuarioService.registrar(estadoUsuario));
    }


    @PutMapping("/actualizar")
    public  ResponseEntity<EstadoUsuario>actualizarEstado(@RequestBody EstadoUsuario estadoUsuario){
        return ResponseEntity.ok(estadoUsuarioService.actualizar(estadoUsuario));
    }

}
