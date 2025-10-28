package com.red_social.auth_service.service;

import com.red_social.auth_service.constants.RolesConstants;
import com.red_social.auth_service.dto.request.RegisterRequest;
import com.red_social.auth_service.exception.ResourceAlreadyExistsException;
import com.red_social.auth_service.exception.ResourceNotFoundException;
import com.red_social.auth_service.model.Admin;
import com.red_social.auth_service.model.Login;
import com.red_social.auth_service.repository.AdminRepository;
import com.red_social.auth_service.util.SecuenciaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private  final LoginService loginService;

    public List<Admin> listarUsername(String username) {
        return adminRepository.findByLogin_Username(username);
    }

    public List<Admin> listarEmail(String email) {
        return adminRepository.findByLogin_Email(email);
    }

    public List<Admin> listarTelefono(String telefono) {
        return adminRepository.findByLogin_Telefono(telefono);
    }

    public List<Admin> listar() {
        return adminRepository.findAll();
    }

    public Admin listarId(String codigo) {
        return adminRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado con código: " + codigo));
    }
    public Admin registrar(RegisterRequest registerRequest) {

        String ultimoCodigoLogin = loginService.ultimoCodigo();
        String nuevoCodigoLogin = SecuenciaUtil.generarSiguienteCodigo(ultimoCodigoLogin);
        loginService.registrar(nuevoCodigoLogin, registerRequest.getUsername(), registerRequest.getEmail(), registerRequest.getTelefono(), registerRequest.getPassword(), RolesConstants.ROLE_MODERADOR);

        Login login = loginService.listarId(nuevoCodigoLogin)
                .orElseThrow(() -> new ResourceAlreadyExistsException("Error al obtener login creado"));


        String ultimoCodigoUsuario = ultimoCodigo();
        String nuevoCodigoUsuario = SecuenciaUtil.generarSiguienteCodigo(ultimoCodigoUsuario);

        Admin admin = Admin.builder()
                .codigo(nuevoCodigoUsuario)
                .nombre(registerRequest.getNombre())
                .apellido(registerRequest.getApellido())
                .edad(registerRequest.getEdad())
                .fechaNacimiento(registerRequest.getFechaNacimiento())
                .genero(registerRequest.getGenero())
                .login(login)
                .build();

        return adminRepository.save(admin);
    }

    public String ultimoCodigo() {
        return adminRepository.obtenerCodigo();
    }
}
