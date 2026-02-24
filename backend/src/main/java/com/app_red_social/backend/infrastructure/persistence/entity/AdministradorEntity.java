package com.app_red_social.backend.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "administrador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdministradorEntity {

    @Id
    @Column(name = "ad_codigo", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "ad_nombre")
    private String nombre;

    @Column(name = "ad_apellido")
    private String apellido;

    @Column(name = "ad_edad")
    private Integer edad;

    @Column(name = "ad_fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "ad_perfil")
    private String perfil;

    @Column(name = "ad_genero")
    private String genero;

    @Column(name = "ad_nacionalidad")
    private String nacionalidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "us_login", referencedColumnName = "lg_codigo")
    private LoginEntity login;
}
