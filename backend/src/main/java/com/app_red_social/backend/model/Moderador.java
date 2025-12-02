package com.app_red_social.backend.model;


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
@ToString

public class Moderador {
    @Id
    @Column(name = "mod_codigo", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "mod_nombre", length = 255)
    private String nombre;

    @Column(name = "mod_apellido", length = 255)
    private String apellido;

    @Column(name = "mod_edad")
    private Integer edad;

    @Column(name = "mod_fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "mod_nacionalidad")
    private String nacionalidad;

    @Lob
    @Column(name = "mod_perfil", columnDefinition = "LONGBLOB")
    private byte[] perfil;

    @Column(name = "mod_genero" , length = 50)
    private String genero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "us_login", referencedColumnName = "lg_codigo")
    private Login login;

}
