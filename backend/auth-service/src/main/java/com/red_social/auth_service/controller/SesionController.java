package com.red_social.auth_service.controller;

import com.red_social.auth_service.dto.request.SessionRequest;
import com.red_social.auth_service.model.Sesion;
import com.red_social.auth_service.service.SesionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sesion")
@Tag(name = "Sesion")
public class SesionController {

    private final SesionService sesionService;

    @GetMapping("/listar")
    public ResponseEntity<List<Sesion>> listar() {
        return ResponseEntity.ok(sesionService.listar());
    }

    @GetMapping("/listarId/{codigo}")
    public ResponseEntity<Sesion> listarId(@PathVariable String codigo) {
        return ResponseEntity.ok(sesionService.listarId(codigo));
    }

    @GetMapping("/listarUsename/{username}")
    public ResponseEntity<List<Sesion>> listarUsername(@PathVariable String username) {
        return ResponseEntity.ok(sesionService.listarPorUsername(username));
    }

    @PostMapping("/registrar")
    public ResponseEntity<Sesion> registrar(@RequestBody SessionRequest sessionRequest) {
        return ResponseEntity.ok(sesionService.registrar(sessionRequest));
    }
}


