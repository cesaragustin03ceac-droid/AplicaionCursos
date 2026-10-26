package com.example.appcursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataRegistroCurso(
    @SerialName("id_curso") val idCurso: Int = 0,
    @SerialName("matricula") val matricula: String = "",
    @SerialName("fecha") val fecha: String = "",
    @SerialName("correo") val correo: String = ""
)

@Serializable
data class RegistroCursoResponse(
    @SerialName("exito") val exito: Boolean = false,
    @SerialName("mensaje") val mensaje: String = ""
)