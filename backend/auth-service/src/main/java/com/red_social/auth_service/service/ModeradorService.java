package com.red_social.auth_service.service;

import com.red_social.auth_service.constants.RolesConstants;
import com.red_social.auth_service.dto.request.RegisterRequest;
import com.red_social.auth_service.exception.ResourceAlreadyExistsException;
import com.red_social.auth_service.exception.ResourceNotFoundException;
import com.red_social.auth_service.model.Login;
import com.red_social.auth_service.model.Moderador;
import com.red_social.auth_service.repository.ModeradorRepository;
import com.red_social.auth_service.util.SecuenciaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModeradorService {

    private final ModeradorRepository moderadorRepository;
    private final LoginService loginService;

    public List<Moderador> listarUsername(String username) {
        return moderadorRepository.findByLogin_Username(username);
    }

    public List<Moderador> listarEmail(String email) {
        return moderadorRepository.findByLogin_Email(email);
    }

    public List<Moderador> listarTelefono(String telefono) {
        return moderadorRepository.findByLogin_Telefono(telefono);
    }

    public List<Moderador> listar() {
        return moderadorRepository.findAll();
    }

    public Moderador listarId(String codigo) {
        return moderadorRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado con código: " + codigo));
    }


    public Moderador registrar(RegisterRequest registerRequest) {

        String ultimoCodigoLogin = loginService.ultimoCodigo();
        String nuevoCodigoLogin = SecuenciaUtil.generarSiguienteCodigo(ultimoCodigoLogin);
        loginService.registrar(nuevoCodigoLogin, registerRequest.getUsername(), registerRequest.getEmail(), registerRequest.getTelefono(), registerRequest.getPassword(), RolesConstants.ROLE_MODERADOR);

        Login login = loginService.listarId(nuevoCodigoLogin)
                .orElseThrow(() -> new ResourceAlreadyExistsException("Error al obtener login creado"));


        String ultimoCodigoUsuario = ultimoCodigo();
        String nuevoCodigoUsuario = SecuenciaUtil.generarSiguienteCodigo(ultimoCodigoUsuario);

        Moderador moderador = Moderador.builder()
                .codigo(nuevoCodigoUsuario)
                .nombre(registerRequest.getNombre())
                .apellido(registerRequest.getApellido())
                .edad(registerRequest.getEdad())
                .fechaNacimiento(registerRequest.getFechaNacimiento())
                .genero(registerRequest.getGenero())
                .login(login)
                .build();

        return moderadorRepository.save(moderador);
    }

    public String ultimoCodigo() {
        return moderadorRepository.obtenerCodigo();
    }
}
