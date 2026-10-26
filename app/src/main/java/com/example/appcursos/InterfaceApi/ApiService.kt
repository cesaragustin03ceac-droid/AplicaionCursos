package com.example.appcursos.InterfaceApi

import com.example.appcursos.modelo.DataVerRegistro
import com.example.appcursos.modelo.RegistroCursoRequest
import com.example.appcursos.modelo.RegistroCursoResponse
import com.example.appcursos.modelo.dataCategoria
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @GET("obtener_carreras.php")
    suspend fun obtenerCarrera(): List<dataCategoria>
    @GET("obtener_registros.php")
    suspend fun obtenerRegistros(): List<DataVerRegistro>
    @POST("registro_curso.php")
    suspend fun registrarCurso(
        @Body request: RegistroCursoRequest
    ): RegistroCursoResponse
}