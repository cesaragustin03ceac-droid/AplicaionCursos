<?php
header("Content-Type: application/json; charset=utf-8");
require_once "config/conexion.php";

try {
    $stmt = $pdo->query("SELECT id_carrera, nombre_carrera FROM carreras ORDER BY nombre_carrera ASC");
    $carreras = $stmt->fetchAll();

    echo json_encode($carreras, JSON_UNESCAPED_UNICODE);

} catch (Exception $e) {
    http_response_code(500);
    echo json_encode(["error" => "Error al obtener carreras: " . $e->getMessage()], JSON_UNESCAPED_UNICODE);
}