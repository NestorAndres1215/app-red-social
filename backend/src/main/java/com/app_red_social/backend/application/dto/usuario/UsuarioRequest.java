package com.app_red_social.backend.application.dto.usuario;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {

    @NotBlank(message = "EL USERNAME ES OBLIGATORIO.")
    private String username;

    @NotBlank(message = "EL EMAIL ES OBLIGATORIO.")
    @Email(message = "EL CORREO NO TIENE UN FORMATO VÁLIDO.")
    private String email;

    @NotBlank(message = "LA CONTRASEÑA ES OBLIGATORIA.")
    @Size(min = 6, message = "LA CONTRASEÑA DEBE TENER AL MENOS 6 CARACTERES.")
    private String password;

    @NotBlank(message = "EL TELÉFONO ES OBLIGATORIO.")
    @Pattern(regexp = "^[0-9]{9}$", message = "EL TELÉFONO DEBE TENER 9 DÍGITOS NUMÉRICOS.")
    private String telefono;

    private String estado;
    private String rol;

    @NotBlank(message = "EL NOMBRE ES OBLIGATORIO.")
    private String nombre;

    @NotBlank(message = "EL APELLIDO ES OBLIGATORIO.")
    private String apellido;

    @NotNull(message = "LA FECHA DE NACIMIENTO ES OBLIGATORIA.")
    @Past(message = "LA FECHA DE NACIMIENTO DEBE SER UNA FECHA PASADA.")
    private LocalDate fechaNacimiento;


    private String banner;
    private String perfil;
    private String provider;
    private String ciudad;

    @NotBlank(message = "EL GÉNERO ES OBLIGATORIO.")
    private String genero;

    @NotBlank(message = "LA NACIONALIDAD ES OBLIGATORIA.")
    private String nacionalidad;

    private String pais;
    private Integer seguidos;
    private Integer publicaciones;
    private String presentacion;
    private String verificado;
    private Boolean cuentaPrivada;
}