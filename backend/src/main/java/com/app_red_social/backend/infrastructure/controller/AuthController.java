package com.app_red_social.backend.infrastructure.controller;


import com.app_red_social.backend.application.dto.auth.LoginRequest;
import com.app_red_social.backend.application.dto.auth.TokenResponse;
import com.app_red_social.backend.domain.model.Login;
import com.app_red_social.backend.domain.port.usecases.AuthUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticacion")
public class AuthController {

    private final AuthUseCase authService;

    @PostMapping("/generate-token")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/actual-usuario")
    public ResponseEntity<Login> obtenerUsuarioActual(Authentication authentication) {
        return ResponseEntity.ok(authService.actualUsuario(authentication));
    }
}