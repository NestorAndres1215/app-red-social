package com.app_red_social.backend.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "empresa")
public class EmpresaEntity {

    @Id
    @Column(name = "emp_codigo", length = 8, nullable = false, unique = true)
    private String codigo;

    @Column(name = "emp_nombre", length = 250, nullable = false, unique = true)
    private String nombre;

    @Column(name = "emp_logo", length = 250, nullable = false, unique = true)
    private String logo;

    @Column(name = "emp_ruc", length = 11, unique = true)
    private String ruc;

    @Column(name = "emp_descripcion", length = 500)
    private String descripcion;

    @Column(name = "emp_telefono", length = 20)
    private String telefono;

    @Column(name = "emp_email", length = 150)
    private String email;

    @Column(name = "emp_web", length = 200)
    private String sitioWeb;

    @Column(name = "emp_direccion", length = 250)
    private String direccion;

    @Column(name = "emp_ciudad", length = 100)
    private String ciudad;

    @Column(name = "emp_departamento", length = 100)
    private String departamento;

    @Column(name = "emp_pais", length = 100)
    private String pais;

    @Column(name = "emp_estado", length = 20, nullable = false)
    private String estado;

    @Column(name = "emp_tipo_empresa", length = 100)
    private String tipoEmpresa;

    @Column(name = "emp_fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "emp_fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

}
