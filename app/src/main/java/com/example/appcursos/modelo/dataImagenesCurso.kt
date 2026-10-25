package com.example.appcursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataImagenesCurso(
    @SerialName("id") val id: Int = 0,
    @SerialName("salon") val salon: String = "",
    @SerialName("url_imagen") val urlImagen: String = ""
)