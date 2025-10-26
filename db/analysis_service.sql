create database analysis_service_db;

use analysis_service_db;


CREATE TABLE estadisticas_edad (
  ee_codigo varchar(8) NOT NULL,
  ee_fecha_actualizacion datetime(6) NOT NULL,
  ee_nuevo_semana int NOT NULL,
  ee_porcentaje double NOT NULL,
  ee_rango_edad varchar(255) NOT NULL,
  ee_total_usuarios int NOT NULL,
  PRIMARY KEY (ee_codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE estadisticas_estado (
  est_codigo varchar(8) NOT NULL,
  est_estado varchar(255) NOT NULL,
  est_fecha_actualizacion datetime(6) NOT NULL,
  est_nuevo_semana int NOT NULL,
  est_porcentaje double NOT NULL,
  est_total_usuarios int NOT NULL,
  PRIMARY KEY (est_codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE estadisticas_genero (
  eg_codigo varchar(8) NOT NULL,
  eg_fecha_actualizacion datetime(6) NOT NULL,
  eg_genero varchar(255) NOT NULL,
  eg_nuevo_semana int NOT NULL,
  eg_porcentaje double NOT NULL,
  eg_total_usuarios int NOT NULL,
  PRIMARY KEY (eg_codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE estadisticas_pais (
  ep_codigo varchar(8) NOT NULL,
  ep_fecha_actualizacion datetime(6) NOT NULL,
  ep_nuevo_semana int NOT NULL,
  ep_pais varchar(100) NOT NULL,
  ep_porcentaje double NOT NULL,
  ep_total_usuarios int NOT NULL,
  PRIMARY KEY (ep_codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE estadisticas_proveedor (
  epr_codigo varchar(8) NOT NULL,
  epr_fecha_actualizacion datetime(6) NOT NULL,
  epr_nuevo_semana int NOT NULL,
  epr_porcentaje double NOT NULL,
  epr_proveedor varchar(100) NOT NULL,
  epr_total_usuarios int NOT NULL,
  PRIMARY KEY (epr_codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE estadisticas_roles (
  er_codigo varchar(8) NOT NULL,
  er_fecha_actualizacion datetime(6) NOT NULL,
  er_nuevo_semana int NOT NULL,
  er_porcentaje double NOT NULL,
  er_roles varchar(255) NOT NULL,
  er_total_usuarios int NOT NULL,
  PRIMARY KEY (er_codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE estadisticas_semana (
  es_codigo varchar(36) NOT NULL,
  es_fecha_actualizacion datetime(6),
  es_fecha_registro date NOT NULL,
  es_mes_anio varchar(6) NOT NULL,
  es_numero_semana int NOT NULL,
  es_porcentaje_cambio double,
  es_primer_dia_semana datetime(6) NOT NULL,
  es_total_registros int NOT NULL,
  es_ultimo_dia_semana datetime(6) NOT NULL,
  PRIMARY KEY (es_codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE estadisticas_verificacion (
  ev_codigo varchar(8) NOT NULL,
  ev_estado varchar(45) NOT NULL,
  ev_fecha_actualizacion datetime(6) NOT NULL,
  ev_porcentaje double NOT NULL,
  ev_total_usuarios int NOT NULL,
  PRIMARY KEY (ev_codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE evento_usuario (
  eu_codigo varchar(8) NOT NULL,
  eu_apellido varchar(255) NOT NULL,
  eu_edad int,
  eu_estado varchar(100) NOT NULL,
  eu_fecha_registro datetime(6),
  eu_genero varchar(50),
  eu_nombre varchar(255) NOT NULL,
  eu_pais varchar(100),
  eu_rol varchar(100) NOT NULL,
  eu_username varchar(100),
  eu_verificado varchar(100) NOT NULL,
  eu_proveedor varchar(100),
  PRIMARY KEY (eu_codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
