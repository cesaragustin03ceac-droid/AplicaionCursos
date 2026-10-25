package com.example.appcursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class dataCursos(
    @SerialName ("id_curso") val idCurso: Int = 0,
    @SerialName ("id_carera") val idCarrera: Int = 0,
    @SerialName ("codigo") val codigo: String = "",
    @SerialName ("nombre_curso") val nombreCurso: String = "",
    @SerialName ("descripcion") val descripcion: String = "Sin descripcion",
    @SerialName ("salon") val salon: String = "",
    @SerialName ("total_horas") val totalHoras: String = "",
    @SerialName ("horario") val horario: String = "",
    @SerialName ("url_imagen") val urlImagen: String = "",
    @SerialName ("cupo_maximo") val cupoMaximo: Int = 0

)
