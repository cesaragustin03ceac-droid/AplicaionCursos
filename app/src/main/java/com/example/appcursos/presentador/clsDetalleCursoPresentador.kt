package com.example.appcursos.presentador

import com.example.appcursos.modelo.clsCursosModelo
import com.example.appcursos.vista.DetalleCursoActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsDetalleCursoPresentador(private val vista: DetalleCursoActivity) {

    private val modelo= clsCursosModelo()

    fun cargarDetalleCurso(idCurso: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val curso = modelo.obtenerDetalleCurso(idCurso)
                vista.mostrarCurso(curso)

            } catch (e: Exception) {
                vista.mostrarError("Error al cargar detalle:"+e.localizedMessage)
            }
        }
    }
}