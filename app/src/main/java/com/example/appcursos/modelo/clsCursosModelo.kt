package com.example.appcursos.modelo

class clsCursosModelo {

    suspend fun obtenerCursosPorCarrera(idCarrera: Int): List<DataImagenesCurso>{
        return RetrofitCliente.apiService.obtenerCursos(idCarrera)
    }

    suspend fun obtenerDetalleCurso(idCurso: Int): dataCursos {
        return RetrofitCliente.apiService.detalleCurso(idCurso)
    }

}