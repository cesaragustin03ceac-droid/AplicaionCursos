package com.example.appcursos.modelo

import kotlinx.serialization.Serializable

@Serializable
data class RegistroCursoRequest(
    val matricula: String,
    val fecha: String,
    val correo: String
)

@Serializable
data class RegistroCursoResponse(
    val exito: Boolean,
    val mensaje: String
)

class clsRegistroCursoModelo {

    suspend fun registrarAlumno(matricula: String, fecha: String, correo: String): RegistroCursoResponse {
        val request = RegistroCursoRequest(matricula, fecha, correo)
        return RetrofitCliente.apiService.registrarCurso(request)
    }
}