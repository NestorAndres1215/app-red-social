package com.red_social.auth_service.service;

import com.red_social.auth_service.constants.AuthConstants;
import com.red_social.auth_service.constants.EstadoConstants;
import com.red_social.auth_service.constants.RolesConstants;
import com.red_social.auth_service.dto.request.RegisterRequest;
import com.red_social.auth_service.dto.response.UsuarioDetalleResponse;
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


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final LoginService loginService;
    private final KafkaProducerService kafkaProducerService;

    public List<Usuario> listarUsername(String username) {
        return usuarioRepository.findByLogin_Username(username);
    }

    public List<Usuario> listarEmail(String email) {
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
    }

    public Usuario registrar(RegisterRequest registerRequest) {
        String ultimoCodigoLogin = loginService.ultimoCodigo();
        String nuevoCodigoLogin = SecuenciaUtil.generarSiguienteCodigo(ultimoCodigoLogin);

        loginService.registrar(nuevoCodigoLogin, registerRequest.getUsername(), registerRequest.getEmail(), registerRequest.getTelefono(), registerRequest.getPassword(), RolesConstants.ROLE_USER);

        Login login = loginService.listarId(nuevoCodigoLogin)
                .orElseThrow(() -> new ResourceAlreadyExistsException("Error al obtener login creado"));

        String ultimoCodigoUsuario = ultimoCodigo();
        String nuevoCodigoUsuario = SecuenciaUtil.generarSiguienteCodigo(ultimoCodigoUsuario);

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

        kafkaProducerService.publishEvent(userEvent);

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
    public List<UsuarioDetalleResponse> listarUsuarios() {
        List<Object[]> results = usuarioRepository.listarUsuariosConDetalleNative();
        List<UsuarioDetalleResponse> lista = new ArrayList<>();

        for (Object[] row : results) {
            LocalDate fechaNacimiento = null;
            if (row[4] != null) {
                fechaNacimiento = ((java.sql.Date) row[4]).toLocalDate(); // ✅ convertir
            }

            LocalDateTime ultimoLogin = null;
            if (row[15] != null) {
                ultimoLogin = ((java.sql.Timestamp) row[15]).toLocalDateTime(); // ✅ convertir
            }

            lista.add(new UsuarioDetalleResponse(
                    (String) row[0], // codigo
                    (String) row[1], // nombre
                    (String) row[2], // apellido
                    row[3] != null ? ((Number) row[3]).intValue() : null, // edad
                    fechaNacimiento, // fechaNacimiento
                    (String) row[5], // genero
                    (String) row[6], // nacionalidad
                    (String) row[7], // presentacion
                    (String) row[8], // photoUrl
                    (byte[]) row[9],  // banner
                    (byte[]) row[10], // perfil
                    (String) row[11], // correo
                    (String) row[12], // telefono
                    (String) row[13], // username
                    (String) row[14], // rolNombre
                    ultimoLogin,      // ultimoLogin
                    (String) row[16]  // estadoNombre
            ));
        }

        return lista;
    }

}
