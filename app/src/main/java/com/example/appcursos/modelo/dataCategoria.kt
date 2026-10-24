package com.example.appcursos.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class dataCategoria(
    @SerialName("id_carrera") val id:Int=0,
    @SerialName("nombre_carrera") val categoria: String=""
)

{
    override fun toString(): String {
        return categoria
    }
}
