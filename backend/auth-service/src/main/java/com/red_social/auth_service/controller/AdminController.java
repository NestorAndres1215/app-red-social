package com.red_social.auth_service.controller;

import com.red_social.auth_service.dto.request.RegisterRequest;
import com.red_social.auth_service.model.Admin;
import com.red_social.auth_service.service.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/administradores")
@Tag(name = "Administradores")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/listar")
    public ResponseEntity<List<Admin>> listar() {
        return ResponseEntity.ok(adminService.listar());
    }

    @GetMapping("/listarId/{codigo}")
    public ResponseEntity<Admin> listarId(@PathVariable String codigo) {
        return ResponseEntity.ok(adminService.listarId(codigo));
    }

    @GetMapping("/listarUsename/{username}")
    public ResponseEntity<List<Admin>> listarUsername(@PathVariable String username) {
        return ResponseEntity.ok(adminService.listarUsername(username));
    }

    @GetMapping("/listarEmail/{email}")
    public ResponseEntity<List<Admin>> listarEmail(@PathVariable String email) {
        return ResponseEntity.ok(adminService.listarEmail(email));
    }

    @GetMapping("/listarTelefono/{telefono}")
    public ResponseEntity<List<Admin>> listarTelefono(@PathVariable String telefono) {
        return ResponseEntity.ok(adminService.listarTelefono(telefono));
    }


    @PostMapping("/registrar")
    public ResponseEntity<Admin> registrarAdministrador(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(adminService.registrar(request));
    }
}
