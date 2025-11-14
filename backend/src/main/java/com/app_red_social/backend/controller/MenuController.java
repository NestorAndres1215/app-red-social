package com.app_red_social.backend.controller;

import com.app_red_social.backend.model.Menu;
import com.app_red_social.backend.model.Rol;
import com.app_red_social.backend.service.MenuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
@Tag(name = "Menu")
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/listar/menuPrimero")
    public ResponseEntity<List<Menu>> menuPrimero() {
        return ResponseEntity.ok(menuService.obtenerMenuPorNivel(1));
    }

    @GetMapping("/listar/menuSegundo")
    public ResponseEntity<List<Menu>> menuSegundo() {
        return ResponseEntity.ok(menuService.obtenerMenuPorNivel(2));
    }

    @GetMapping("/listar/menuTercero")
    public ResponseEntity<List<Menu>> menuTercero() {
        return ResponseEntity.ok(menuService.obtenerMenuPorNivel(3));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Menu>> listar() {
        return ResponseEntity.ok(menuService.listar());
    }

    @GetMapping("/listar/roles")
    public ResponseEntity<List<Menu>> listarPorRol(@RequestBody Rol rol) {
        return ResponseEntity.ok(menuService.listarRoles(rol));
    }

    @GetMapping("/listar/{codigo}")
    public ResponseEntity<Menu> listarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(menuService.listarCodigo(codigo));
    }

    @GetMapping("/listar/{rolCodigo}")
    public ResponseEntity<List<Menu>> obtenerMenusPorDosRoles(@PathVariable String rolCodigo) {
        return ResponseEntity.ok(menuService.obtenerMenusPorDosRoles(rolCodigo));
    }
}
