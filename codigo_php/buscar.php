<?php
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type");

require_once "config/conexion.php";

$datos = json_decode(file_get_contents("php://input"));
$matricula = isset($datos->matricula) ? trim($datos->matricula) : "";

if (empty($matricula)) {
    http_response_code(400);
    echo json_encode([
        "exito" => false,
        "mensaje" => "Debe ingresar la matrícula"
    ]);
    exit;
}

try {
    // Solo consultamos la matrícula y el correo
    $stmt = $pdo->prepare("SELECT matricula, correo FROM alumnos WHERE matricula = ? LIMIT 1");
    $stmt->execute([$matricula]);
    $alumno = $stmt->fetch(PDO::FETCH_ASSOC);

    if ($alumno) {
        echo json_encode([
            "exito" => true,
            "mensaje" => "Alumno encontrado.",
            "matricula" => $alumno['matricula'],
            "correo" => $alumno['correo']
        ]);
    } else {
        echo json_encode([
            "exito" => false,
            "mensaje" => "El alumno no se encuentra registrado."
        ]);
    }
} catch (Exception $e) {
    http_response_code(500);
    echo json_encode([
        "exito" => false,
        "mensaje" => "Error en el servidor: " . $e->getMessage()
    ]);
}
?>