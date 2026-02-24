package com.app_red_social.backend.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "moderador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModeradorEntity {

    @Id
    @Column(name = "mod_codigo", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "mod_nombre")
    private String nombre;

    @Column(name = "mod_apellido")
    private String apellido;

    @Column(name = "mod_edad")
    private Integer edad;

    @Column(name = "mod_fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "mod_nacionalidad")
    private String nacionalidad;

    @Column(name = "mod_perfil")
    private String perfil;

    @Column(name = "mod_genero" , length = 50)
    private String genero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "us_login", referencedColumnName = "lg_codigo")
    private LoginEntity login;

}
