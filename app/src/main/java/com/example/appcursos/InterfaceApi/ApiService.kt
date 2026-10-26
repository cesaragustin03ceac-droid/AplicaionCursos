package com.example.appcursos.InterfaceApi

import com.example.appcursos.modelo.DataImagenesCurso
import com.example.appcursos.modelo.DataVerRegistro
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
    @GET("api_ver_registros.php")
    suspend fun obtenerRegistros(@Query("id_carrera") idCarrera: Int = 0): List<DataVerRegistro>
    @GET("obtener_cursos.php")
    suspend fun obtenerCursos(@Query("carrera_id") carreraId: Int): List<DataImagenesCurso>

    @GET("obtener_registros.php")
    suspend fun obtenerRegistros(): List<DataVerRegistro>
    @POST("registrar_curso.php") // Cambia esto por el nombre exacto de tu archivo PHP en el servidor
    suspend fun registrarCurso(@Body request: RegistroCursoRequest): RegistroCursoResponse
}
