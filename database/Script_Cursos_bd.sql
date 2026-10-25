CREATE DATABASE cursos_db;
USE cursos_db;

CREATE TABLE alumnos (
  id_alumno INT(11) NOT NULL AUTO_INCREMENT,
  matricula VARCHAR(10) NOT NULL UNIQUE,
  correo VARCHAR(100) NOT NULL UNIQUE,
  PRIMARY KEY (id_alumno)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE cursos (
  id_curso INT(11) NOT NULL AUTO_INCREMENT,
  id_carrera INT(11) NOT NULL,
  nombre_curso VARCHAR(100) NOT NULL,
  descripcion TEXT DEFAULT NULL,
  salon VARCHAR(100) NOT NULL,
  total_horas INT NOT NULL,
  dias_atencion VARCHAR(100) NOT NULL,
  horario VARCHAR(100) NOT NULL,
  cupo_maximo INT NOT NULL,
  url_imagen_principal VARCHAR(150) DEFAULT NULL,
  PRIMARY KEY (id_curso),
  KEY fk_cursos_carreras (id_carrera),
  CONSTRAINT fk_cursos_carreras FOREIGN KEY (id_carrera) 
    REFERENCES carreras (id_carrera) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


