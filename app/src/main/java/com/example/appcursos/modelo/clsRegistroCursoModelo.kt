package com.example.appcursos.modelo

class clsRegistroCursoModelo {

    suspend fun registrarAlumno(idCurso: Int, matricula: String, fecha: String, correo: String): RegistroCursoResponse {
        val request = dataRegistroCurso(
            idCurso = idCurso,
            matricula = matricula,
            fecha = fecha,
            correo = correo
        )
        return RetrofitCliente.apiService.registrarCurso(request)
    }
    suspend fun buscarAlumno(matricula: String):dataBuscarResponse{
        val request=dataBuscarRequest(matricula)
        return RetrofitCliente.apiService.Buscar(request)
    }
}