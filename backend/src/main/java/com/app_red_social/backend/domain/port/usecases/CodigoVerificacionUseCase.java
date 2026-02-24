package com.app_red_social.backend.domain.port.usecases;

import com.app_red_social.backend.domain.model.CodigoVerificacion;
import jakarta.mail.MessagingException;
import java.util.List;


public interface CodigoVerificacionUseCase {

    List<CodigoVerificacion> listar();

    CodigoVerificacion verificacionRecuperacionContrasena(String correo);

    CodigoVerificacion registrar(CodigoVerificacion codigoVerificacion);

    CodigoVerificacion verificacionCodigo(String codigo);

    CodigoVerificacion listarCodigo(String codigo);

    void codigoVerificacionCorreo(CodigoVerificacion verificacion) throws MessagingException;
}
