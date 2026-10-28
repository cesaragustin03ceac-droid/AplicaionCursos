CREATE TABLE `inscripciones` (
  `id_inscripcion` INT(11) NOT NULL AUTO_INCREMENT,
  `id_alumno` INT(11) NOT NULL,
  `id_curso` INT(11) NOT NULL,
  `fecha_registro` DATE NOT NULL,
  PRIMARY KEY (`id_inscripcion`),
  UNIQUE KEY `uq_alumno_curso` (`id_alumno`, `id_curso`),
  KEY `id_curso` (`id_curso`),
  CONSTRAINT `inscripciones_ibfk_1` FOREIGN KEY (`id_alumno`) 
    REFERENCES `alumnos` (`id_alumno`) ON DELETE CASCADE,
  CONSTRAINT `inscripciones_ibfk_2` FOREIGN KEY (`id_curso`) 
    REFERENCES `cursos` (`id_curso`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `inscripciones` (`id_inscripcion`, `id_alumno`, `id_curso`, `fecha_registro`) VALUES
(1, 1, 1, '2026-09-01'),
(2, 2, 3, '2026-09-01'),
(3, 3, 4, '2026-09-02'),
(4, 4, 7, '2026-09-03');
