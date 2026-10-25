package com.example.appcursos.modelo

class clsVerRegistrosModelo {
    suspend fun obtenerCarreras(): List<dataCategoria>{
        val datos=RetrofitCliente.apiService.obtenerCarrera()
        val listaFinal=mutableListOf(dataCategoria(0,"seleccione una carerra"))
        listaFinal.addAll(datos)
        return listaFinal
    }

}