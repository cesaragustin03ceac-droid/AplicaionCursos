package com.example.appcursos.modelo

class clsCarrerasModelo {
    suspend fun obtenerCursos(): List<dataCategoria>{
        val datos= RetrofitCliente.apiService.obtenerCarrera()

        val listaFinal=mutableListOf(dataCategoria(0,"Seleccionar una Carrera"))
        listaFinal.addAll(datos)
        return listaFinal
    }
}