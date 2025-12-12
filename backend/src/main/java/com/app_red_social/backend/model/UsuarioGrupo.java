package com.app_red_social.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario_grupo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UsuarioGrupo {

    @Id
    @Column(name = "ug_codigo", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "ug_nombre")
    private String nombre;

    @Column(name = "ug_descripcion")
    private String descripcion;

    @Column(name = "ug_foto_perfil")
    private String foto;

    @Column(name = "ug_privacidad")
    private String privacidad;

    @Column(name = "ug_estado")
    private String estado;

    @Column(name = "ug_creador")
    private String creador;

    @Column(name = "ug_fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "ug_fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

}
