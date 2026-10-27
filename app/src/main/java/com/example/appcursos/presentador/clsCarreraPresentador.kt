package com.example.appcursos.presentador

import com.example.appcursos.modelo.clsCarrerasModelo
import com.example.appcursos.modelo.clsCursosModelo
import com.example.appcursos.modelo.dataCategoria
import com.example.appcursos.vista.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsCarreraPresentador (private val vista: MainActivity) {
    private val modeloCursos = clsCursosModelo()
    private val modeloCarreras = clsCarrerasModelo()
    private var listaCategoria: List<dataCategoria> = emptyList()

    fun inciar(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                listaCategoria =modeloCarreras.obtenerCursos()
                vista.mostrarCategorias(listaCategoria)
                cargarCursos(0)
            }catch (e: Exception){
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }
    fun seleccionarCarrera(posicion: Int){
        if (posicion>0&& posicion <listaCategoria.size){
            val categoria=listaCategoria[posicion]
            //vista.mostrarMensajeExito(categoria.id,categoria.categoria)

            cargarCursos(categoria.id)
        }
    }

    private fun cargarCursos(carreraId :Int){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val cursos= modeloCursos.obtenerCursosPorCarrera(carreraId)
                vista.mostrarCursos(cursos)
            }catch (e: Exception){
                vista.mostrarError("Error al cargar cursos:" + e.localizedMessage)
            }
        }
    }
}