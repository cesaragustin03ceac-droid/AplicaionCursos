package com.example.appcursos.presentador
import com.example.appcursos.modelo.clsVerRegistrosModelo
import com.example.appcursos.modelo.dataCategoria
import com.example.appcursos.vista.VerRegistroActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class clsVerRegistrosPresentador(private val vista: VerRegistroActivity) {
    private val modelo = clsVerRegistrosModelo()
    private var listaCarreras: List<dataCategoria> = emptyList()
    fun iniciar() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                listaCarreras = modelo.obtenerCarreras()
                vista.mostrarCarreras(listaCarreras)

            } catch (e: Exception) {
                vista.mostrarError("error:" + e.localizedMessage)
            }
        }
    }
    fun seleccionarCarrera(idCarrera: Int) {
        if (idCarrera > 0) {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val listaCursos=modelo.obtenerCursosPorCarrera(idCarrera)
                    val totalInscritos=modelo.calcularTotalInscritos(listaCursos)
                    vista.mostrarTotalInscritos(totalInscritos)
                    vista.mostrarRegistros(listaCursos)
                } catch (e: Exception) {
                    vista.mostrarError("Error: " + e.localizedMessage)
                }
            }
        } else {
            vista.mostrarTotalInscritos(0)
            vista.mostrarRegistros(emptyList())
        }
    }
}