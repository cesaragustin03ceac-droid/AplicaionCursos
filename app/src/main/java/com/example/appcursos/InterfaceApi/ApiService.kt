package com.example.appcursos.InterfaceApi

import com.example.appcursos.modelo.DataImagenesCurso
import com.example.appcursos.modelo.DataVerRegistro
import com.example.appcursos.modelo.dataCategoria
import com.example.appcursos.modelo.RegistroCursoRequest
import com.example.appcursos.modelo.RegistroCursoResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {
    @GET("obtener_carreras.php")
    suspend fun obtenerCarrera(): List<dataCategoria>
    @GET("api_ver_registros.php")
    suspend fun obtenerRegistros(@Query("id_carrera") idCarrera: Int = 0): List<DataVerRegistro>
    @GET("obtener_cursos.php")
    suspend fun obtenerCursos(@Query("carrera_id") carreraId: Int): List<DataImagenesCurso>

    @GET("obtener_registros.php")
    suspend fun obtenerRegistros(): List<DataVerRegistro>

    @POST("registrar_curso.php")
    suspend fun registrarCurso(@Body request: RegistroCursoRequest): RegistroCursoResponse
}
