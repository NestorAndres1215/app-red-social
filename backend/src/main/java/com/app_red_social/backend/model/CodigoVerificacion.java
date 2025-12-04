package com.app_red_social.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "codigo_verificacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CodigoVerificacion {

    @Id
    @Column(name = "cv_codigo")
    private String codigo;

    @Column(name = "cv_correo")
    private String correo;

    @Column(name = "cv_usuario")
    private String usuario;

    @Column(name = "cv_codigo_verificado")
    private String codigo_verificacion;

    @Column(name = "cv_fecha_generacion")
    private LocalDateTime fecha_generacion;


}
