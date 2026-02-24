package com.app_red_social.backend.infrastructure.controller;

import com.app_red_social.backend.domain.model.Menu;
import com.app_red_social.backend.domain.model.Rol;
import com.app_red_social.backend.domain.port.usecases.MenuUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
@Tag(name = "Menu")
public class MenuController {

    private final MenuUseCase menuService;

    @GetMapping("/listar/nivel/primero")
    public ResponseEntity<List<Menu>> menuPrimero() {
        return ResponseEntity.ok(menuService.obtenerMenuPorNivel(1));
    }

    @GetMapping("/listar/nivel/segundo")
    public ResponseEntity<List<Menu>> menuSegundo() {
        return ResponseEntity.ok(menuService.obtenerMenuPorNivel(2));
    }

    @GetMapping("/listar/nivel/tercero")
    public ResponseEntity<List<Menu>> menuTercero() {
        return ResponseEntity.ok(menuService.obtenerMenuPorNivel(3));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Menu>> listar() {
        return ResponseEntity.ok(menuService.listarTodos());
    }

    @GetMapping("/listar/roles")
    public ResponseEntity<List<Menu>> listarPorRol(@RequestBody Rol rol) {
        return ResponseEntity.ok(menuService.listarRoles(rol));
    }

    @GetMapping("/listar/codigo/{codigo}")
    public ResponseEntity<Optional<Menu>> listarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(menuService.listarCodigo(codigo));
    }

    @GetMapping("/listar/roles/codigo/{codigo}")
    public ResponseEntity<List<Menu>> obtenerMenusPorDosRoles(@PathVariable String codigo) {
        return ResponseEntity.ok(menuService.obtenerMenusPorDosRoles(codigo));
    }
}
