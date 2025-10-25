create database auth_service_db;

use auth_service_db;


CREATE TABLE admin (
  ad_codigo varchar(8) NOT NULL,
  ad_apellido varchar(255) NOT NULL,
  ad_edad int DEFAULT NULL,
  ad_fecha_nacimiento date NOT NULL,
  ad_genero varchar(255) DEFAULT NULL,
  ad_nombre varchar(255) NOT NULL,
  ad_perfil longblob,
  us_login varchar(8) DEFAULT NULL,
  PRIMARY KEY (ad_codigo),
  FOREIGN KEY (us_login) REFERENCES login (lg_codigo)
);


CREATE TABLE codigo_verificacion (
  cv_codigo varchar(8) NOT NULL,
  cv_codigo_verificacion varchar(10) NOT NULL,
  cv_correo varchar(100) NOT NULL,
  cv_fecha_generacion date DEFAULT NULL,
  cv_hora_generacion time(6) DEFAULT NULL,
  cv_usuario varchar(255) NOT NULL,
  PRIMARY KEY (cv_codigo)
);

DROP TABLE IF EXISTS estados_usuario;
CREATE TABLE estados_usuario (
  st_codigo varchar(8) NOT NULL,
  st_descripcion varchar(255) NOT NULL,
  st_nombre varchar(255) NOT NULL,
  PRIMARY KEY (st_codigo),
  UNIQUE (st_nombre)
);

INSERT INTO estados_usuario VALUES
('ST000001','Usuario activo y habilitado','ACTIVO'),
('ST000002','Usuario inactivo o con acceso restringido','INACTIVO');


CREATE TABLE rol (
  tr_codigo varchar(8) NOT NULL,
  tr_descripcion varchar(255) NOT NULL,
  tr_nombre varchar(255) NOT NULL,
  PRIMARY KEY (tr_codigo),
  UNIQUE (tr_nombre)
);

INSERT INTO rol VALUES
('RL000001','Usuario con permisos básicos','ROLE_USER'),
('RL000002','Administrador con acceso total','ROLE_ADMIN'),
('RL000003','Moderador con permisos intermedios','ROLE_MODERADOR');


CREATE TABLE login (
  lg_codigo varchar(8) NOT NULL,
  lg_correo varchar(100) NOT NULL,
  lg_fecha_creacion datetime(6) DEFAULT NULL,
  lg_contrasena varchar(255) NOT NULL,
  lg_telefono varchar(255) NOT NULL,
  lg_ultimo_login datetime(6) DEFAULT NULL,
  lg_username varchar(255) NOT NULL,
  lg_estado_usuario varchar(8) DEFAULT NULL,
  lg_rol varchar(8) DEFAULT NULL,
  PRIMARY KEY (lg_codigo),
  UNIQUE (lg_correo),
  UNIQUE (lg_telefono),
  UNIQUE (lg_username),
  FOREIGN KEY (lg_estado_usuario) REFERENCES estados_usuario (st_codigo),
  FOREIGN KEY (lg_rol) REFERENCES rol (tr_codigo)
);


CREATE TABLE moderador (
  mod_codigo varchar(8) NOT NULL,
  mod_apellido varchar(255) NOT NULL,
  mod_edad int DEFAULT NULL,
  mod_fecha_nacimiento date NOT NULL,
  mod_genero varchar(50) DEFAULT NULL,
  mod_nombre varchar(255) NOT NULL,
  mod_perfil longblob,
  us_login varchar(8) DEFAULT NULL,
  PRIMARY KEY (mod_codigo),
  FOREIGN KEY (us_login) REFERENCES login (lg_codigo)
);

CREATE TABLE sesion (
  se_codigo varchar(8) NOT NULL,
  se_fin_sesion datetime(6) DEFAULT NULL,
  se_inicio_sesion datetime(6) NOT NULL,
  se_navegador varchar(100) DEFAULT NULL,
  se_ubicacion varchar(255) DEFAULT NULL,
  se_login varchar(8) DEFAULT NULL,
  PRIMARY KEY (se_codigo),
  FOREIGN KEY (se_login) REFERENCES login (lg_codigo)
);

CREATE TABLE suspensiones (
  sus_codigo varchar(8) NOT NULL,
  sus_motivo_corto varchar(700) NOT NULL,
  sus_fecha_expiracion date DEFAULT NULL,
  sus_fecha_suspension date DEFAULT NULL,
  sus_hora_suspension time(6) DEFAULT NULL,
  sus_usuario varchar(8) DEFAULT NULL,
  PRIMARY KEY (sus_codigo),
  FOREIGN KEY (sus_usuario) REFERENCES login (lg_codigo)
);

CREATE TABLE revisiones_suspension (
  rs_codigo varchar(8) NOT NULL,
  rs_estado_revision varchar(255) NOT NULL,
  rs_fecha_revision date NOT NULL,
  rs_observacion varchar(500) DEFAULT NULL,
  rs_moderador varchar(8) DEFAULT NULL,
  rs_suspension varchar(8) DEFAULT NULL,
  PRIMARY KEY (rs_codigo),
  FOREIGN KEY (rs_moderador) REFERENCES moderador (mod_codigo),
  FOREIGN KEY (rs_suspension) REFERENCES suspensiones (sus_codigo)
);

CREATE TABLE token (
  tk_codigo varchar(8) NOT NULL,
  tk_fecha_creacion datetime(6) DEFAULT NULL,
  tk_fecha_expiracion datetime(6) NOT NULL,
  tk_token varchar(512) NOT NULL,
  tk_valido varchar(100) NOT NULL,
  tk_login varchar(8) DEFAULT NULL,
  PRIMARY KEY (tk_codigo),
  UNIQUE (tk_token),
  FOREIGN KEY (tk_login) REFERENCES login (lg_codigo)
);

CREATE TABLE usuario (
  us_codigo varchar(8) NOT NULL,
  us_apellido varchar(100) NOT NULL,
  us_banner longblob,
  us_edad int DEFAULT NULL,
  us_fecha_nacimiento date NOT NULL,
  us_genero varchar(100) DEFAULT NULL,
  us_nacionalidad varchar(100) DEFAULT NULL,
  us_nombre varchar(100) NOT NULL,
  us_perfil longblob,
  us_photo_url varchar(100) DEFAULT NULL,
  us_presentacion varchar(500) DEFAULT NULL,
  us_provider varchar(100) DEFAULT NULL,
  us_login varchar(8) DEFAULT NULL,
  PRIMARY KEY (us_codigo),
  FOREIGN KEY (us_login) REFERENCES login (lg_codigo)
);
