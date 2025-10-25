package com.red_social.auth_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "estados_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EstadoUsuario {

    @Id
    @Column(name = "st_codigo", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "st_nombre",unique = true)
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 255, message = "El nombre no puede superar los 255 caracteres")
    private String nombre;

    @Column(name = "st_descripcion")
    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    private String descripcion;
}
