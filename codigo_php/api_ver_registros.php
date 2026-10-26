<?php

header("Content-Type: application/json; charset=UTF-8"); 
header("Access-Control-Allow-Origin: *");
require_once "config/conexion.php"; 

$id_carrera = isset($_GET['id_carrera']) ? intval($_GET['id_carrera']) : 0; 

try {
    $sql = "SELECT 
                c.id_curso,
                c.id_carrera,
                c.nombre_curso,
                c.cupo_maximo,
                COUNT(i.id_inscripcion) AS inscritos_actuales,
                COALESCE(c.url_imagen, '') AS url_imagen
            FROM cursos c
            LEFT JOIN inscripciones i ON c.id_curso = i.id_curso";

    if ($id_carrera > 0) {
        $sql .= " WHERE c.id_carrera = ? GROUP BY c.id_curso ORDER BY c.nombre_curso ASC";
        $stmt = $pdo->prepare($sql);
        $stmt->execute([$id_carrera]);
    } else {
        $sql .= " GROUP BY c.id_curso ORDER BY c.nombre_curso ASC";
        $stmt = $pdo->query($sql);
    }

    $registros = $stmt->fetchAll(PDO::FETCH_ASSOC);

    foreach ($registros as &$reg) {
        $reg['id_curso'] = (int)$reg['id_curso'];
        $reg['id_carrera'] = (int)$reg['id_carrera'];
        $reg['cupo_maximo'] = (int)$reg['cupo_maximo'];
        $reg['inscritos_actuales'] = (int)$reg['inscritos_actuales'];
    }

    echo json_encode($registros);

} catch (Exception $e) {
    header("HTTP/1.1 500 Internal Server Error");
    echo json_encode([
        "error" => "Error al obtener los registros de inscripciones: " . $e->getMessage()
    ]);
}
?>
