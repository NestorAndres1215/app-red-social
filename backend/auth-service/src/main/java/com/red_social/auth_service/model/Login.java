package com.red_social.auth_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "login")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Login {

    @Id
    @Column(name = "lg_codigo", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "lg_username")
    private String username;

    @Column(name = "lg_correo")
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico no tiene un formato válido")
    @Size(max = 100, message = "El correo electrónico no puede superar los 100 caracteres")
    private String email;

    @Column(name = "lg_telefono")
    private String telefono;

    @Column(name = "lg_contrasena")
    private String password;

    @Column(name = "lg_ultimoLogin")
    private LocalDateTime ultimoLogin;

    @Column(name = "lg_fechaCreacion")
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "lg_estadoUsuario", referencedColumnName = "st_codigo")
    private EstadoUsuario estadoUsuario;

    @ManyToOne
    @JoinColumn(name = "lg_rol", referencedColumnName = "tr_codigo")
    private Rol rol;


}
