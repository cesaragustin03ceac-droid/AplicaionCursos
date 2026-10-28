package com.example.appcursos.modelo

class clsDetalleCursosModelo {

    suspend fun obtenerDetalleCurso(idCurso: Int): dataCursos {
        return RetrofitCliente.apiService.detalleCurso(idCurso)
    }
}