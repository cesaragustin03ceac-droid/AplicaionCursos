CREATE DATABASE cursos_db
USE cursos_db;

CREATE TABLE alumnos (
  id_alumno INT(11) NOT NULL AUTO_INCREMENT,
  matricula VARCHAR(20) NOT NULL,
  nombre_completo VARCHAR(150) NOT NULL,
  correo VARCHAR(150) NOT NULL,
  PRIMARY KEY (id_alumno),
  UNIQUE KEY matricula (matricula),
  UNIQUE KEY correo (correo)
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO alumnos (id_alumno, matricula, nombre_completo, correo) VALUES
(1, '202611001', 'Mariana Valenzuela Morales', 'mariana.valenzuela@correo.edu.mx'),
(2, '202611002', 'Fernando Daniel Mendoza', 'fernando.mendoza@correo.edu.mx'),
(3, '202611003', 'Valeria Sofia Cruz', 'valeria.cruz@correo.edu.mx'),
(4, '202611004', 'Hector Manuel Pacheco', 'hector.pacheco@correo.edu.mx');

CREATE TABLE carreras (
  id_carrera INT(11) NOT NULL AUTO_INCREMENT,
  nombre_carrera VARCHAR(150) NOT NULL,
  PRIMARY KEY (id_carrera),
  UNIQUE KEY nombre_carrera (nombre_carrera)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO carreras (id_carrera, nombre_carrera) VALUES
(3, 'Administración y Finanzas'),
(1, 'Ingeniería en Desarrollo de Software'),
(2, 'Ingeniería Industrial');

CREATE TABLE cursos (
  id_curso INT(11) NOT NULL AUTO_INCREMENT,
  id_carrera INT(11) NOT NULL,
  codigo VARCHAR(20) NOT NULL,
  nombre_curso VARCHAR(150) NOT NULL,
  descripcion TEXT NOT NULL,
  salon VARCHAR(100) NOT NULL,
  total_horas VARCHAR(50) NOT NULL,
  horario VARCHAR(150) NOT NULL,
  url_imagen VARCHAR(255) DEFAULT NULL,
  cupo_maximo INT(11) NOT NULL DEFAULT 50,
  PRIMARY KEY (id_curso),
  UNIQUE KEY codigo (codigo),
  KEY id_carrera (id_carrera),
  CONSTRAINT cursos_ibfk_1 FOREIGN KEY (id_carrera)
    REFERENCES carreras (id_carrera) ON DELETE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO cursos (id_curso, id_carrera, codigo, nombre_curso, descripcion, salon, total_horas, horario, url_imagen, cupo_maximo) VALUES
(1, 1, 'SOFT-101', 'Desarrollo Web Full Stack', 'Construcción de aplicaciones web interactivas con frontend reactivo y APIs REST seguras.', 'Laboratorio de Cómputo 1', '60 horas', 'Lunes a Jueves 08:00 - 10:00', 'uploads/cursos/principales/programacion_avanzada.png', 35),
(2, 1, 'SOFT-102', 'Seguridad en Aplicaciones y Nube', 'Auditoría de vulnerabilidades, autenticación OAuth2 y buenas prácticas en la nube.', 'Laboratorio de Cómputo 2', '45 horas', 'Martes y Jueves 10:00 - 12:30', 'uploads/cursos/principales/seguridad_nube.png', 30),
(3, 1, 'SOFT-103', 'Desarrollo de Aplicaciones Móviles', 'Creación de interfaces fluidas y gestión de persistencia local en dispositivos inteligentes.', 'Laboratorio de Cómputo 3', '50 horas', 'Viernes 08:00 - 13:00', 'uploads/cursos/principales/apps_moviles.png', 35),
(4, 2, 'IND-201', 'Optimización de Procesos y Manufactura', 'Implementación de metodologías Lean, reducción de mermas y balanceo de líneas de ensamble.', 'Taller Industrial 1', '60 horas', 'Lunes a Viernes 07:00 - 09:00', 'uploads/cursos/principales/optimizacion_procesos.png', 40),
(5, 2, 'IND-202', 'Control Estadístico de la Calidad', 'Gráficos de control, análisis de capacidad de procesos y muestreo de aceptación.', 'Aula A-204', '40 horas', 'Lunes y Miércoles 11:00 - 13:00', 'uploads/cursos/principales/control_calidad.png', 45),
(6, 2, 'IND-203', 'Logística y Cadenas de Suministro', 'Modelos de inventarios, ruteo de entregas y diseño de redes de distribución comercial.', 'Aula A-205', '45 horas', 'Martes y Jueves 13:00 - 15:30', 'uploads/cursos/principales/logistica_suministro.png', 40),
(7, 3, 'FIN-301', 'Finanzas Corporativas y Valuación', 'Evaluación de proyectos de inversión, costo de capital y análisis de flujos de efectivo.', 'Aula Magna B-1', '50 horas', 'Lunes a Miércoles 09:00 - 11:00', 'uploads/cursos/principales/finanzas_corporativas.png', 50),
(8, 3, 'FIN-302', 'Contabilidad y Análisis Financiero', 'Interpretación de balances generales, estados de resultados y cálculo de razones de liquidez.', 'Aula Magna B-2', '40 horas', 'Jueves y Viernes 09:00 - 11:30', 'uploads/cursos/principales/analisis_financiero.png', 50),
(9, 3, 'FIN-303', 'Gestión Estratégica del Talento', 'Modelos de liderazgo organizacional, retención de capital humano y métricas de desempeño.', 'Aula Magna B-3', '35 horas', 'Sábados 08:00 - 13:00', 'uploads/cursos/principales/gestion_talento.png', 45);

CREATE TABLE inscripciones (
  id_inscripcion INT(11) NOT NULL AUTO_INCREMENT,
  id_alumno INT(11) NOT NULL,
  id_curso INT(11) NOT NULL,
  fecha_registro DATE NOT NULL,
  PRIMARY KEY (id_inscripcion),
  UNIQUE KEY uq_alumno_curso (id_alumno, id_curso),
  KEY id_curso (id_curso),
  CONSTRAINT inscripciones_ibfk_1 FOREIGN KEY (id_alumno)
    REFERENCES alumnos (id_alumno) ON DELETE CASCADE,
  CONSTRAINT inscripciones_ibfk_2 FOREIGN KEY (id_curso)
    REFERENCES cursos (id_curso) ON DELETE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO inscripciones (id_inscripcion, id_alumno, id_curso, fecha_registro) VALUES
(1, 1, 1, '2026-09-01'),
(2, 2, 3, '2026-09-01'),
(3, 3, 4, '2026-09-02'),
(4, 4, 7, '2026-09-03');