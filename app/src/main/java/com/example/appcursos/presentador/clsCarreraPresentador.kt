package com.example.appcursos.presentador

import com.example.appcursos.modelo.clsCarrerasModelo
import com.example.appcursos.modelo.dataCategoria
import com.example.appcursos.vista.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsCarreraPresentador (private val vista: MainActivity) {
    private val modelo= clsCarrerasModelo()
    private var listaCategoria: List<dataCategoria> = emptyList()

    fun inciar(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                listaCategoria =modelo.obtenerCursos()
                vista.mostrarCategorias(listaCategoria)
            }catch (e: Exception){
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }
    fun seleccionarCarrera(posicion: Int){
        if (posicion>0&& posicion <listaCategoria.size){
            val categoria=listaCategoria[posicion]
            vista.mostrarMensajeExito(categoria.id,categoria.categoria)
        }
    }
}