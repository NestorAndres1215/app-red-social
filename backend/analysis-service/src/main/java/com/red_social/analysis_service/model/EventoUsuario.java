package com.red_social.analysis_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "evento_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EventoUsuario {

    @Id
    @Column(name = "eu_codigo", length = 8, nullable = false, unique = true)
    @NotBlank(message = "El código es obligatorio")
    private String codigo;

    @Column(name = "eu_username", length = 100)
    @Size(max = 100, message = "El username no puede superar los 100 caracteres")
    private String username;

    @Column(name = "eu_nombre", length = 255)
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 255, message = "El nombre no puede superar los 255 caracteres")
    private String nombre;

    @Column(name = "eu_apellido", length = 255)
    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 255, message = "El apellido no puede superar los 255 caracteres")
    private String apellido;

    @Column(name = "eu_genero", length = 50)
    @Size(max = 50, message = "El género no puede superar los 50 caracteres")
    private String genero;

    @Column(name = "eu_pais", length = 100)
    @Size(max = 100, message = "El país no puede superar los 100 caracteres")
    private String pais;
    @Column(name = "eu_proveedor", length = 100)
    @Size(max = 100, message = "El proveedor no puede superar los 100 caracteres")
    private String proveedor;


    @Column(name = "eu_edad")
    @Min(value = 0, message = "La edad no puede ser negativa")
    @Max(value = 120, message = "La edad no puede superar los 120 años")
    private Integer edad;

    @Column(name = "eu_rol", length = 100)
    @NotBlank(message = "El rol es obligatorio")
    @Size(max = 100, message = "El rol no puede superar los 100 caracteres")
    private String rol;


    @Column(name = "eu_estado", length = 100)
    @NotBlank(message = "El estado es obligatorio")
    @Size(max = 100, message = "El estado no puede superar los 100 caracteres")
    private String estado;

    @Column(name = "eu_verificado", length = 100)
    @NotNull(message = "El campo verificado es obligatorio")
    private String verificado;
    @Column(name = "eu_fecha_registro")
    private LocalDateTime fechaRegistro;
}
