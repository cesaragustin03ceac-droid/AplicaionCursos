package com.example.appcursos.InterfaceApi

import com.example.appcursos.modelo.DataVerRegistro
import com.example.appcursos.modelo.dataCategoria
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("obtener_carreras.php")
    suspend fun obtenerCarrera(): List<dataCategoria>
    @GET("api_ver_registros.php")
    suspend fun obtenerRegistros(@Query("id_carrera") idCarrera: Int = 0): List<DataVerRegistro>
}