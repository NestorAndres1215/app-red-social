package com.red_social.auth_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Token {

    @Id
    @Column(name = "tk_codigo", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "tk_token", unique = true)
    @NotBlank(message = "El token no puede estar vacío")
    @Size(max = 512, message = "El token no puede superar los 512 caracteres")
    private String token;

    @Column(name = "tk_valido", length = 100)
    @NotBlank(message = "El estado de validez es obligatorio")
    private String valido;

    @Column(name = "tk_fechaExpiracion", nullable = false)
    @Future(message = "La fecha de expiración debe estar en el futuro")
    private LocalDateTime fechaExpiracion;

    @Column(name = "tk_fechaCreacion")
    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tk_login", referencedColumnName = "lg_codigo")
    private Login login;
}
