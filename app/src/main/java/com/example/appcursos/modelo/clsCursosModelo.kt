package com.example.appcursos.modelo

class clsCursosModelo {

    suspend fun obtenerCursosPorCarrera(idCarrera: Int): List<dataCursos>{
        return RetrofitCliente.apiService.obtenerCursos(idCarrera)
    }

    suspend fun obtenerDetalleCurso(idCurso: Int): dataCursos{
        return RetrofitCliente.apiService.detalleCurso(idCurso)
    suspend fun obtenerCursosPorCarrera(carreraId : Int): List<DataImagenesCurso>{
        return RetrofitCliente.apiService.obtenerCursos(carreraId)
    }
}