package com.app_red_social.backend.model;


import jakarta.persistence.*;
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
    private String token;

    @Column(name = "tk_valido", length = 100)
    private String valido;

    @Column(name = "tk_fechaExpiracion", nullable = false)
    private LocalDateTime fechaExpiracion;

    @Column(name = "tk_fecha_registro")
    private LocalDateTime fechaRegistro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tk_login", referencedColumnName = "lg_codigo")
    private Login login;
}