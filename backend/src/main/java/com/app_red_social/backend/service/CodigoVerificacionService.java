package com.app_red_social.backend.service;

import com.app_red_social.backend.model.CodigoVerificacion;
import com.app_red_social.backend.repository.CodigoVerificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodigoVerificacionService {

    private final CodigoVerificacionRepository codigoVerificacionRepository;


    public String ObtenerUltimoCodigo() {
        return codigoVerificacionRepository.obtenerUltimoCodigo();
    }

    public List<CodigoVerificacion> listar() {
        return codigoVerificacionRepository.findAll();
    }


}
