package com.app_red_social.backend.infrastructure.persistence.mapper;

import com.app_red_social.backend.application.dto.administrador.AdministradorActualResponse;
import com.app_red_social.backend.application.dto.usuario.UsuarioActualResponse;
import com.app_red_social.backend.domain.model.Administrador;
import com.app_red_social.backend.infrastructure.persistence.entity.AdministradorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AdministradorMapper {

    private final LoginMapper loginMapper;

    public Administrador toDomain(AdministradorEntity entity) {
        if (entity == null) return null;

        return Administrador.builder()
                .codigo(entity.getCodigo())
                .nombre(entity.getNombre())
                .apellido(entity.getApellido())
                .edad(entity.getEdad())
                .fechaNacimiento(entity.getFechaNacimiento())
                .nacionalidad(entity.getNacionalidad())
                .perfil(entity.getPerfil())
                .genero(entity.getGenero())
                .login(loginMapper.toDomain(entity.getLogin()))
                .build();
    }

    public AdministradorEntity toEntity(Administrador administrador) {
        if (administrador == null) return null;

        return AdministradorEntity.builder()
                .codigo(administrador.getCodigo())
                .nombre(administrador.getNombre())
                .apellido(administrador.getApellido())
                .edad(administrador.getEdad())
                .fechaNacimiento(administrador.getFechaNacimiento())
                .nacionalidad(administrador.getNacionalidad())
                .perfil(administrador.getPerfil())
                .genero(administrador.getGenero())
                .login(loginMapper.toEntity(administrador.getLogin()))
                .build();
    }


    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public AdministradorActualResponse toDto(Object[] row) {

        if (row == null) return null;

        return AdministradorActualResponse.builder()
                .codigoAdministrador((String) row[0])
                .nombre((String) row[1])
                .apellido((String) row[2])
                .nombreCompleto((String) row[3])
                .edad(row[4] != null ? ((Number) row[4]).intValue() : null)
                .fechaNacimiento(row[5] != null ? DATE_FORMAT.format((Date) row[5]) : null)
                .fechaNacimientoFormateada(row[6] != null ? row[6].toString() : null)
                .edadCalculada(row[7] != null ? ((Number) row[7]).intValue() : null)
                .genero((String) row[8])
                .nacionalidad((String) row[9])
                .fotoPerfil((String) row[10])
                .codigoLogin((String) row[11])
                .correo((String) row[12])
                .telefono((String) row[13])
                .username((String) row[14])
                .rol((String) row[15])
                .estadoUsuario((String) row[16])
                .ultimoLogin(row[17] != null ? DATETIME_FORMAT.format((Timestamp) row[17]) : null)
                .fechaRegistro(row[18] != null ? DATETIME_FORMAT.format((Timestamp) row[18]) : null)
                .build();
    }

    public List<AdministradorActualResponse> toList(List<Object[]> rows) {
        return rows.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}