package com.app_red_social.backend.domain.port.repository;

import com.app_red_social.backend.domain.model.CodigoVerificacion;

import java.util.List;
import java.util.Optional;

public interface CodigoVerificacionRepositoryPort {

    String obtenerUltimoCodigo();

    Optional<CodigoVerificacion> findByCorreo(String correo);

    List<CodigoVerificacion> listar();

    CodigoVerificacion verificacionRecuperacionContrasena(CodigoVerificacion codigoVerificacion);

    CodigoVerificacion registrar(CodigoVerificacion codigoVerificacion);

    CodigoVerificacion verificacionCodigo(CodigoVerificacion codigoVerificacion);

    Optional<CodigoVerificacion> listarCodigo(String codigo);
}
