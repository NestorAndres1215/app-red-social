package com.red_social.auth_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @Column(name = "us_nombre", length = 100)
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede superar los 255 caracteres")
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
    @Size(max = 100, message = "El proveedor no puede superar los 100 caracteres")
    private String provider;

    @Column(name = "us_genero",length = 100)
    private String genero;

    @Column(name = "us_nacionalidad", length = 100)
    private String nacionalidad;

    @Column(name = "us_presentacion", length = 500)
    @Size(max = 500, message = "La presentación no puede superar los 500 caracteres")
    private String presentacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "us_login", referencedColumnName = "lg_codigo")
    private Login login;

}
