package com.red_social.auth_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "rol")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Rol {

    @Id
    @Column(name = "tr_codigo",length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "tr_nombre",length = 100, unique = true)
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 255, message = "El nombre no puede superar los 255 caracteres")
    private String nombre;

    @Column(name = "tr_descripcion", length = 255)
    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    private String descripcion;

}
