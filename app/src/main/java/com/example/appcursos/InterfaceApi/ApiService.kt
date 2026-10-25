package com.example.appcursos.InterfaceApi

import com.example.appcursos.modelo.DataVerRegistro
import com.example.appcursos.modelo.dataCategoria
import retrofit2.http.GET


interface ApiService {
    @GET("obtener_carreras.php")
    suspend fun obtenerCarrera(): List<dataCategoria>
    @GET("obtener_registros.php")
    suspend fun obtenerRegistros(): List<DataVerRegistro>
}