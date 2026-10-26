<?php
// detalle_curso.php

header("Content-Type: application/json; charset=UTF-8");
require_once "config/conexion.php";

// 1. Recibimos 'curso_id' (o 'id_curso')
$curso_id = isset($_GET['curso_id']) ? intval($_GET['curso_id']) : (isset($_GET['id_curso']) ? intval($_GET['id_curso']) : 0);

if ($curso_id <= 0) {
    header("HTTP/1.1 400 Bad Request");
    echo json_encode(["error" => "El ID de curso proporcionado no es válido"], JSON_UNESCAPED_UNICODE);
    exit;
}

try {
    // 2. Consultamos directamente la tabla 'cursos'
    $stmt = $pdo->prepare("SELECT c.id_curso, c.id_carrera, c.codigo, c.nombre_curso, c.descripcion, c.salon, c.total_horas, c.horario, c.url_imagen AS url_imagen_principal FROM cursos c WHERE c.id_curso = ?");    $stmt->execute([$curso_id]);
    $curso = $stmt->fetch(PDO::FETCH_ASSOC);

    if ($curso) {
        // 3. Convertimos los tipos numéricos
        $curso['id_curso'] = (int)$curso['id_curso'];
        $curso['id_carrera'] = (int)$curso['id_carrera'];

        // 4. Retornamos la información del curso
        echo json_encode($curso, JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);
    } else {
        header("HTTP/1.1 404 Not Found");
        echo json_encode(["error" => "No se encontró el curso solicitado"], JSON_UNESCAPED_UNICODE);
    }

} catch (Exception $e) {
    header("HTTP/1.1 500 Internal Server Error");
    echo json_encode(["error" => "Error al obtener el detalle del curso: " . $e->getMessage()], JSON_UNESCAPED_UNICODE);
}
?>