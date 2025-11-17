package com.app_red_social.backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sesion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Sesion {

    @Id
    @Column(name = "se_codigo", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "se_inicioSesion", nullable = false)
    private LocalDateTime inicioSesion;

    @Column(name = "se_finSesion")
    private LocalDateTime finSesion;

    @Column(name = "se_ubicacion", length = 255)
    private String ubicacion;

    @Column(name = "se_navegador", length = 100)
    private String navegador;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "se_login", referencedColumnName = "lg_codigo")
    private Login login;

}
