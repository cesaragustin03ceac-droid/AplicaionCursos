package com.example.appcursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataBuscarRequest(
    @SerialName("matricula") val matricula: String=""
)

@Serializable
data class dataBuscarResponse(
    @SerialName("exito") val exito: Boolean=false,
    @SerialName("mensaje") val mensaje: String="",
    @SerialName("matricula") val matricula: String="",
    @SerialName("correo") val correo: String=""
)