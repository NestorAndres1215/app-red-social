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

    @Column(name = "lg_username", unique = true)
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(max = 255, message = "El nombre de usuario no puede superar los 255 caracteres")
    private String username;

    @Column(name = "lg_correo", unique = true)
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico no tiene un formato válido")
    @Size(max = 100, message = "El correo electrónico no puede superar los 100 caracteres")
    private String email;

    @Column(name = "lg_telefono", unique = true)
    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]+$", message = "El teléfono solo puede contener números")
    private String telefono;

    @Column(name = "lg_contrasena")
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 255, message = "La contraseña debe tener al menos 8 caracteres")
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
