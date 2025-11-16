package com.app_red_social.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Usuario {

    @Id
    @Column(name = "us_codigo", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "us_nombre", length = 200)
    private String nombre;

    @Column(name = "us_apellido", length = 100)
    private String apellido;

    @Column(name = "us_edad")
    private Integer edad;

    @Column(name = "us_fechaNacimiento")
    private LocalDate fechaNacimiento;

    @Lob
    @Column(name = "us_perfil", columnDefinition = "LONGBLOB")
    private byte[] perfil;

    @Lob
    @Column(name = "us_banner", columnDefinition = "LONGBLOB")
    private byte[] banner;

    @Column(name = "us_photoUrl" , length = 100)
    private String photoUrl;

    @Column(name = "us_provider", length = 100)
    private String provider;

    @Column(name = "us_genero",length = 100)
    private String genero;

    @Column(name = "us_nacionalidad", length = 100)
    private String nacionalidad;

    @Column(name = "us_ciudad", length = 100)
    private String ciudad;

    @Column(name = "us_pais", length = 100)
    private String pais;

    // Métricas sociales
    @Column(name = "us_seguidores")
    private Integer seguidores;

    @Column(name = "us_seguidos")
    private Integer seguidos;

    @Column(name = "us_publicaciones")
    private Integer publicaciones;

    @Column(name = "us_presentacion", length = 500)
    private String presentacion;

    @Column(name = "us_verificado")
    private String verificado;

    @Column(name = "us_cuenta_privada")
    private Boolean cuentaPrivada;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "us_login", referencedColumnName = "lg_codigo")
    private Login login;

}
