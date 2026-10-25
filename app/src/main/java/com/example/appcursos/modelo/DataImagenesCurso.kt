package com.example.appcursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataImagenesCurso(
    @SerialName("id_curso") val id: Int = 0,
    @SerialName("nombre_curso") val nombreCurso: String="",
    @SerialName("salon") val salon: String = "",
    @SerialName("url_imagen") val urlImagen: String = ""
)