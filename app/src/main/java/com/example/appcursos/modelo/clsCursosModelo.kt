package com.example.appcursos.modelo

class clsCursosModelo {

    suspend fun obtenerCursosPorCarrera(carreraId : Int): List<DataImagenesCurso>{
        return RetrofitCliente.apiService.obtenerCursos(carreraId)
    }
}