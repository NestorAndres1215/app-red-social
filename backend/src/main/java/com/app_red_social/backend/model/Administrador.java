package com.app_red_social.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "administrador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Administrador {

    @Id
    @Column(name = "ad_codigo", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "ad_nombre")
    private String nombre;

    @Column(name = "ad_apellido")
    private String apellido;

    @Column(name = "ad_edad")
    private Integer edad;

    @Column(name = "ad_fechaNacimiento")
    private LocalDate fechaNacimiento;

    @Lob
    @Column(name = "ad_perfil", columnDefinition = "LONGBLOB")
    private byte[] perfil;

    @Column(name = "ad_genero")
    private String genero;

    @Column(name = "ad_nacionalidad")
    private LocalDate nacionalidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "us_login", referencedColumnName = "lg_codigo")
    private Login login;
}
