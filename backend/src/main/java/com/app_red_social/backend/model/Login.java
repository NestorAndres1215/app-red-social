package com.app_red_social.backend.model;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
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

    @Column(name = "lg_username", length = 250)
    private String username;

    @Column(name = "lg_correo", length = 250, nullable = false, unique = true)
    private String email;

    @Column(name = "lg_telefono", length = 250)
    private String telefono;

    @Column(name = "lg_contrasena", length = 250)
    @JsonIgnore
    private String password;

    @Column(name = "lg_ultimo_login")
    private LocalDateTime ultimoLogin;

    @Column(name = "lg_fecha_registro")
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "lg_estado_usuario", referencedColumnName = "st_codigo")
    private EstadoUsuario estadoUsuario;

    @ManyToOne
    @JoinColumn(name = "lg_rol", referencedColumnName = "rl_codigo")
    private Rol rol;

}
