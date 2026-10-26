package com.example.appcursos.modelo

class clsVerRegistrosModelo {
    // funcion que obtiene las carreras para el spiner
    suspend fun obtenerCarreras(): List<dataCategoria> {
        val datos = RetrofitCliente.apiService.obtenerCarrera() // trae las carreras desde la api
        val listaFinal = mutableListOf(dataCategoria(0, "Filtrar por Carrera")) // crea una lista nueva y le ponemos un elemento falso que es el texto
        listaFinal.addAll(datos) // agrefgan todas las carreras que vienien de la api
        return listaFinal // devuelve la lista
    }
    // recibe la lista de cursos y hace la suma
    suspend fun calcularTotalInscritos(listaCursos: List<DataVerRegistro>): Int{
        return listaCursos.sumOf { it.cantidadInscritos }
    }

    // obtiene los cursos por carrera mediante su id
    suspend fun obtenerCursosPorCarrera(idCarrera: Int): List<DataVerRegistro> {
        if (idCarrera == 0) { // si el ides 0 osea el titulo  devuelve una lista vacia
            return emptyList()
        }
        return RetrofitCliente.apiService.obtenerRegistros(idCarrera) // si el id es valido trae  los cursos ppor carrera
    }
}