package com.app_red_social.backend.infrastructure.controller;

import com.app_red_social.backend.application.dto.usuario.RoleUserStatsResponse;
import com.app_red_social.backend.application.dto.usuario.UsuarioActualResponse;
import com.app_red_social.backend.application.dto.usuario.UsuarioListaResponse;
import com.app_red_social.backend.application.dto.usuario.UsuarioRequest;
import com.app_red_social.backend.domain.model.Usuario;
import com.app_red_social.backend.domain.port.usecases.UsuarioUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario")
public class UsuarioController {

    private final UsuarioUseCase usuarioUseCase;

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody UsuarioRequest request) {
        return ResponseEntity.ok(usuarioUseCase.registrar(request));
    }

    @PutMapping("/actualizar/{codigo}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable String codigo, @RequestBody UsuarioRequest request) {
        return ResponseEntity.ok(usuarioUseCase.actualizar(codigo, request));
    }

    @GetMapping("/listar")
    public List<Usuario> listarTodos() {
        return usuarioUseCase.listarTodos();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Usuario> buscarPorUsername(@PathVariable String username) {
        return ResponseEntity.ok(usuarioUseCase.buscarPorUsername(username));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email) {
         return ResponseEntity.ok(usuarioUseCase.buscarPorEmail(email));
    }

    @GetMapping("/telefono/{telefono}")
    public ResponseEntity<Usuario> buscarPorTelefono(@PathVariable String telefono) {
        return ResponseEntity.ok( usuarioUseCase.buscarPorTelefono(telefono));
    }

    @GetMapping("/nombre/{nombre}")
    public List<Usuario> buscarPorNombre(@PathVariable String nombre) {
        return usuarioUseCase.buscarPorNombre(nombre);
    }

    @GetMapping("/apellido/{apellido}")
    public List<Usuario> buscarPorApellido(@PathVariable String apellido) {
        return usuarioUseCase.buscarPorApellido(apellido);
    }

    @GetMapping("/nombre-apellido")
    public List<Usuario> buscarPorNombreYApellido(
            @RequestParam String nombre,
            @RequestParam String apellido) {
        return usuarioUseCase.buscarPorNombreYApellido(nombre, apellido);
    }

    @GetMapping("/ciudad/{ciudad}")
    public List<Usuario> buscarPorCiudad(@PathVariable String ciudad) {
        return usuarioUseCase.buscarPorCiudad(ciudad);
    }

    @GetMapping("/pais/{pais}")
    public List<Usuario> buscarPorPais(@PathVariable String pais) {
        return usuarioUseCase.buscarPorPais(pais);
    }

    @GetMapping("/genero/{genero}")
    public List<Usuario> buscarPorGenero(@PathVariable String genero) {
        return usuarioUseCase.buscarPorGenero(genero);
    }

    @GetMapping("/edad/{edad}")
    public List<Usuario> buscarPorEdad(@PathVariable Integer edad) {
        return usuarioUseCase.buscarPorEdad(edad);
    }

    @GetMapping("/edad-rango")
    public List<Usuario> buscarPorRangoEdad(@RequestParam Integer edadMin, @RequestParam Integer edadMax) {
        return usuarioUseCase.buscarPorRangoEdad(edadMin, edadMax);
    }

    @GetMapping("/privada/{privada}")
    public List<Usuario> buscarPorCuentaPrivada(@PathVariable Boolean privada) {
        return usuarioUseCase.buscarPorCuentaPrivada(privada);
    }

    @GetMapping("/verificado/{verificado}")
    public List<Usuario> buscarPorVerificado(@PathVariable Boolean verificado) {
        return usuarioUseCase.buscarPorVerificado(verificado);
    }

    @GetMapping("/proveedor/{proveedor}")
    public List<Usuario> buscarPorProveedor(@PathVariable String proveedor) {
        return usuarioUseCase.buscarPorProveedor(proveedor);
    }

    @GetMapping("/listar/usuario/actual/{codigo}")
    public List<UsuarioActualResponse> obtener(@PathVariable String codigo) {
        return usuarioUseCase.obtenerUsuarioActual(codigo);
    }

    @GetMapping("/listar/usuarios/normal/{username}/{estado}")
    public ResponseEntity<List<UsuarioListaResponse>> listarUsuariosNormal(@PathVariable String username, @PathVariable String estado) {
        return ResponseEntity.ok(usuarioUseCase.listarUsuarios(2, username, estado));
    }

    @GetMapping("/listar/usuarios/administradores/{username}/{estado}")
    public ResponseEntity<List<UsuarioListaResponse>> listarUsuariosAdmin(@PathVariable String username, @PathVariable String estado) {
        return ResponseEntity.ok(usuarioUseCase.listarUsuarios(1, username, estado));
    }

    @GetMapping("/listar/usuarios/moderador/{username}/{estado}")
    public ResponseEntity<List<UsuarioListaResponse>> listarUsuariosModerador(@PathVariable String username, @PathVariable String estado) {
        return ResponseEntity.ok(usuarioUseCase.listarUsuarios(2, username, estado));
    }

    @GetMapping("/porcentaje/tiempo/{opcion}")
    public List<RoleUserStatsResponse> listarPorcentajeTiempo(@PathVariable int opcion) {
        return usuarioUseCase.listarPorcentajeTiempo(opcion);
    }

}