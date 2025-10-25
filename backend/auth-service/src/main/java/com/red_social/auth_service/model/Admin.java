package com.red_social.auth_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name = "admin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Admin {

    @Id
    @Column(name = "ad_codigo",length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "ad_nombre")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 255, message = "El nombre no puede superar los 255 caracteres")
    private String nombre;

    @Column(name = "ad_apellido")
    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 255, message = "El apellido no puede superar los 255 caracteres")
    private String apellido;

    @Column(name = "ad_edad")
    @Min(value = 18, message = "La edad no puede ser menor de edad")
    @Max(value = 120, message = "La edad no puede superar los 120 años")
    private Integer edad;

    @Column(name = "ad_fechaNacimiento")
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate fechaNacimiento;

    @Lob
    @Column(name = "ad_perfil", columnDefinition = "LONGBLOB")
    private byte[] perfil;

    @Column(name = "ad_genero")
    private String genero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "us_login", referencedColumnName = "lg_codigo")
    private Login login;
}
