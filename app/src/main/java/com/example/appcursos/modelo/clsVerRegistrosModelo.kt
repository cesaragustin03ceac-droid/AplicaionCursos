package com.example.appcursos.modelo

class clsVerRegistrosModelo {
    suspend fun obtenerCarreras(): List<dataCategoria> {
        val datos = RetrofitCliente.apiService.obtenerCarrera()
        val listaCarreras = mutableListOf(dataCategoria(0, "Filtrar por Carrera"))
        listaCarreras.addAll(datos)
        return listaCarreras
    }
    suspend fun calcularTotalInscritos(listaCursos: List<DataVerRegistro>): Int{
        return listaCursos.sumOf { it.cantidadInscritos }
    }
    suspend fun obtenerCursosPorCarrera(idCarrera: Int): List<DataVerRegistro> {
        return RetrofitCliente.apiService.obtenerRegistros(idCarrera)
    }
}