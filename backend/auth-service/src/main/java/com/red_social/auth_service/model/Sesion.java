package com.red_social.auth_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sesion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Sesion {
    @Id
    @Column(name = "se_codigo", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "se_inicioSesion", nullable = false)
    @PastOrPresent(message = "La fecha de inicio de sesión no puede estar en el futuro")
    private LocalDateTime inicioSesion;

    @Column(name = "se_finSesion")
    @FutureOrPresent(message = "La fecha de fin de sesión no puede ser anterior a la de inicio")
    private LocalDateTime finSesion;

    @Column(name = "se_ubicacion", length = 255)
    @Size(max = 255, message = "La ubicación no puede superar los 255 caracteres")
    private String ubicacion;

    @Column(name = "se_navegador", length = 100)
    @Size(max = 100, message = "El nombre del navegador no puede superar los 100 caracteres")
    private String navegador;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "se_login", referencedColumnName = "lg_codigo")
    private Login login;
}
