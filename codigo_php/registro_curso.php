<?php
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

require_once "config/conexion.php";

$datos = json_decode(file_get_contents("php://input"));

if (
    !empty($datos->matricula) &&
    !empty($datos->fecha) &&
    !empty($datos->correo)
) {
    $matricula = trim($datos->matricula);
    $correo    = trim($datos->correo);
    $fecha     = trim($datos->fecha);

    $id_curso  = !empty($datos->id_curso) ? intval($datos->id_curso) : 1;

    try {

        $sqlAlumno = "SELECT id_alumno FROM alumnos WHERE matricula = ? LIMIT 1";
        $stmtAlumno = $pdo->prepare($sqlAlumno);
        $stmtAlumno->execute([$matricula]);
        $alumno = $stmtAlumno->fetch(PDO::FETCH_ASSOC);

        if ($alumno) {
            $id_alumno = $alumno['id_alumno'];
        } else {
            $nombreInicial = "Alumno " . $matricula;
            $sqlNuevoAlumno = "INSERT INTO alumnos (matricula, nombre_completo, correo) VALUES (?, ?, ?)";
            $stmtNuevo = $pdo->prepare($sqlNuevoAlumno);
            $stmtNuevo->execute([$matricula, $nombreInicial, $correo]);
            $id_alumno = $pdo->lastInsertId();
        }

        $sqlCheck = "SELECT id_inscripcion FROM inscripciones WHERE id_alumno = ? AND id_curso = ? LIMIT 1";
        $stmtCheck = $pdo->prepare($sqlCheck);
        $stmtCheck->execute([$id_alumno, $id_curso]);

        if ($stmtCheck->rowCount() > 0) {
            http_response_code(200);
            echo json_encode([
                "exito" => false,
                "mensaje" => "El alumno ya se encuentra inscrito en este curso."
            ]);
            exit;
        }

        $sqlInscripcion = "INSERT INTO inscripciones (id_alumno, id_curso, fecha_registro) VALUES (?, ?, ?)";
        $stmtInscripcion = $pdo->prepare($sqlInscripcion);
        $resultado = $stmtInscripcion->execute([$id_alumno, $id_curso, $fecha]);

        if ($resultado) {
            http_response_code(200);
            echo json_encode([
                "exito" => true,
                "mensaje" => "Inscripción registrada exitosamente."
            ]);
        } else {
            http_response_code(200);
            echo json_encode([
                "exito" => false,
                "mensaje" => "No se pudo registrar la inscripción."
            ]);
        }

    } catch (PDOException $e) {
        http_response_code(200);
        echo json_encode([
            "exito" => false,
            "mensaje" => "Error en la base de datos: " . $e->getMessage()
        ]);
    }
} else {
    http_response_code(200);
    echo json_encode([
        "exito" => false,
        "mensaje" => "Datos incompletos. Se requiere matrícula, fecha y correo."
    ]);
}
?>