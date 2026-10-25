package com.example.appcursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class DataVerRegistro(
    //  recibe los datos de la api y los transforma en un objeto de kotlin
    @SerialName("id_curso") val idCurso: Int = 0,
    @SerialName("id_carrera") val idCarrera: Int = 0,
    @SerialName("nombre_curso") val nombreCurso: String,
    @SerialName("cupo_maximo") val totalCupos: Int,
    @SerialName("inscritos_actuales") val cantidadInscritos: Int,
    @SerialName("url_imagen") val urlImagen: String
)