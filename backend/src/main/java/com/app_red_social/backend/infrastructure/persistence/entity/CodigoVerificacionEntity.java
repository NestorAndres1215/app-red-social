package com.app_red_social.backend.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "codigo_verificacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodigoVerificacionEntity {

    @Id
    @Column(name = "cv_codigo")
    private String codigo;

    @Column(name = "cv_correo")
    private String correo;

    @Column(name = "cv_usuario")
    private String usuario;

    @Column(name = "cv_tipo_verificado")
    private String tipoVerificacion;

    @Column(name = "cv_codigo_verificado")
    private String codigoVerificacion;

    @Column(name = "cv_fecha_generacion")
    private LocalDateTime fechaGeneracion;

}
