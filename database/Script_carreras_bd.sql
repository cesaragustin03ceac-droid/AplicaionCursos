CREATE TABLE `carreras` (
  `id_carrera` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_carrera` varchar(150) NOT NULL,
  PRIMARY KEY (`id_carrera`),
  UNIQUE KEY `nombre_carrera` (`nombre_carrera`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `carreras` (`id_carrera`, `nombre_carrera`) VALUES 
(3, 'Administración y Finanzas'),
(1, 'Ingeniería en Desarrollo de Software'),
(2, 'Ingeniería Industrial');
