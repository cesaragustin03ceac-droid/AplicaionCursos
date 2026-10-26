package com.example.appcursos.InterfaceApi

import com.example.appcursos.modelo.dataCategoria
import com.example.appcursos.modelo.dataCursos
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("obtener_carreras.php")
    suspend fun obtenerCarrera(): List<dataCategoria>

    @GET("obtener_cursos.php")
    suspend fun obtenerCursos(@Query("id_carrera") cursoId:Int ): List<dataCursos>

    @GET("detalle_curso.php")
    suspend fun detalleCurso(@Query("id_curso") cursoId: Int): dataCursos
}