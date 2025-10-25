package com.red_social.auth_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @Column(name = "mod_codigo",length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "mod_nombre", length = 255)
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 255, message = "El nombre no puede superar los 255 caracteres")
    private String nombre;

    @Column(name = "mod_apellido",length = 255)
    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 255, message = "El apellido no puede superar los 255 caracteres")
    private String apellido;

    @Column(name = "mod_edad")
    @Min(value = 18, message = "La edad no puede ser menor de edad")
    @Max(value = 120, message = "La edad no puede superar los 120 años")
    private Integer edad;

    @Column(name = "mod_fechaNacimiento")
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate fechaNacimiento;

    @Lob
    @Column(name = "mod_perfil", columnDefinition = "LONGBLOB")
    private byte[] perfil;

    @Column(name = "mod_genero" , length = 50)
    private String genero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "us_login", referencedColumnName = "lg_codigo")
    private Login login;

}
