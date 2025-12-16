package com.app_red_social.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario_grupo_miembro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UsuarioGrupoMiembro {
    @Id
    @Column(name = "ugm_codigo", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "ugm_estado_miembro")
    private String estadoMiembro;

    @Column(name = "ugm_fecha_ingreso")
    private LocalDateTime fechaRegistro;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ugm_usuario_grupo", referencedColumnName = "ug_codigo")
    private UsuarioGrupo usuarioGrupo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ugm_login", referencedColumnName = "lg_codigo")
    private Login login;
}
