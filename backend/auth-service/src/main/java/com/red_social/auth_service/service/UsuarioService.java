package com.red_social.auth_service.service;

import com.red_social.auth_service.constants.AuthConstants;
import com.red_social.auth_service.constants.EstadoConstants;
import com.red_social.auth_service.constants.RolesConstants;
import com.red_social.auth_service.dto.request.RegisterRequest;
import com.red_social.auth_service.dto.response.UsuarioDTO;

import com.red_social.auth_service.exception.ResourceAlreadyExistsException;
import com.red_social.auth_service.exception.ResourceNotFoundException;
import com.red_social.auth_service.kafka.KafkaProducerService;
import com.red_social.auth_service.kafka.UserEvent;
import com.red_social.auth_service.model.Login;
import com.red_social.auth_service.model.Usuario;
import com.red_social.auth_service.repository.UsuarioRepository;
import com.red_social.auth_service.util.SecuenciaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final LoginService loginService;
    private final KafkaProducerService kafkaProducerService;

    public List<Usuario> listarUsername(String username) {
        return usuarioRepository.findByLogin_Username(username);
    }

    public Optional<Usuario> listarEmail(String email) {
        return usuarioRepository.findByLogin_Email(email);
    }

    public List<Usuario> listarTelefono(String telefono) {
        return usuarioRepository.findByLogin_Telefono(telefono);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario listarId(String codigo) {
        return usuarioRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con código: " + codigo));
    }

    public Usuario loginRegisterGoogle(Map<String, Object> userInfo) {
        String email = (String) userInfo.get("email");
        String name = (String) userInfo.get("name");
        String photoUrl = (String) userInfo.get("picture");
        return usuarioRepository.findByLogin_Email(email).map(login -> {
            login.setNombre(name);
            login.setPhotoUrl(photoUrl);
            return usuarioRepository.save(login);
        }).orElseGet(() -> {
            try {
                String ultimoCodigoLogin = loginService.ultimoCodigo();
                String nuevoCodigoLogin = SecuenciaUtil.generarSiguienteCodigo(ultimoCodigoLogin);
                loginService.registrar(nuevoCodigoLogin, "", email, "", "", RolesConstants.ROLE_USER);

                Login login = loginService.listarId(nuevoCodigoLogin)
                        .orElseThrow(() -> new ResourceAlreadyExistsException("Error al obtener login creado"));

                String ultimoCodigoUsuario = ultimoCodigo();
                String nuevoCodigoUsuario = SecuenciaUtil.generarSiguienteCodigo(ultimoCodigoUsuario);
                Usuario usuario = Usuario.builder()
                        .codigo(nuevoCodigoUsuario)
                        .nombre(name)
                        .photoUrl(photoUrl)
                        .provider(AuthConstants.GOOGLE)
                        .login(login)
                        .build();

                return usuarioRepository.save(usuario);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        });


    }

    public Usuario registrar(RegisterRequest registerRequest) {
        String ultimoCodigoLogin = loginService.ultimoCodigo();
        String nuevoCodigoLogin = SecuenciaUtil.generarSiguienteCodigo(ultimoCodigoLogin);

        loginService.registrar(nuevoCodigoLogin, registerRequest.getUsername(), registerRequest.getEmail(), registerRequest.getTelefono(), registerRequest.getPassword(), RolesConstants.ROLE_USER);

        Login login = loginService.listarId(nuevoCodigoLogin)
                .orElseThrow(() -> new ResourceAlreadyExistsException("Error al obtener login creado"));

        String ultimoCodigoUsuario = ultimoCodigo();
        String nuevoCodigoUsuario = SecuenciaUtil.generarSiguienteCodigo(ultimoCodigoUsuario);
/*
        UserEvent userEvent = UserEvent.builder()
                .eventType("USER_REGISTERED")
                .userId(nuevoCodigoUsuario)
                .username(registerRequest.getUsername())
                .nombre(registerRequest.getNombre())
                .apellido(registerRequest.getApellido())
                .genero(registerRequest.getGenero())
                .pais(registerRequest.getNacionalidad())
                .edad(registerRequest.getEdad())
                .rol(RolesConstants.ROLE_USER)
                .estado(EstadoConstants.ACTIVO)
                .verificado("NO VERIFICADO")
                .proveedor(AuthConstants.LOCAL)
                .fechaNacimiento(registerRequest.getFechaNacimiento())
                .fechaRegistro(LocalDateTime.now())
                .build();

        kafkaProducerService.publishEvent(userEvent);*/

        Usuario usuario = Usuario.builder()
                .codigo(nuevoCodigoUsuario)
                .nombre(registerRequest.getNombre())
                .apellido(registerRequest.getApellido())
                .edad(registerRequest.getEdad())
                .fechaNacimiento(registerRequest.getFechaNacimiento())
                .provider(AuthConstants.LOCAL)
                .genero(registerRequest.getGenero())
                .nacionalidad(registerRequest.getNacionalidad())
                .login(login)
                .build();
        return usuarioRepository.save(usuario);
    }


    public String ultimoCodigo() {
        return usuarioRepository.obtenerCodigo();
    }

    public UsuarioDTO getUsuarioByCodigo(String codigo) {
        List<Object[]> result = usuarioRepository.spGetUsuarioPorCodigo("1", codigo);

        if (result.isEmpty()) {
            return null;
        }

        Object[] obj = result.get(0);
        UsuarioDTO dto = new UsuarioDTO();

        dto.setCodigoUsuario((String) obj[0]);
        dto.setNombre((String) obj[1]);
        dto.setApellido((String) obj[2]);
        dto.setEdad(obj[3] != null ? ((Number) obj[3]).intValue() : null);
        dto.setFechaNacimiento(obj[4] != null ? obj[4].toString() : null);
        dto.setGenero((String) obj[5]);
        dto.setNacionalidad((String) obj[6]);
        dto.setPresentacion((String) obj[7]);
        dto.setPhotoUrl((String) obj[8]);
        dto.setBanner((String) obj[9]);
        dto.setPerfil((String) obj[10]);
        dto.setCorreo((String) obj[11]);
        dto.setTelefono((String) obj[12]);
        dto.setUsername((String) obj[13]);
        dto.setRol((String) obj[14]);
        dto.setUltimoLogin(obj[15] != null ? obj[15].toString() : null);
        dto.setFechaCreacion(obj[16] != null ? obj[16].toString() : null);
        dto.setCodigoLogin((String) obj[17]);
        dto.setEstadoNombre((String) obj[18]);
        dto.setEstadoDescripcion((String) obj[19]);

        return dto;
    }


}
