package com.example.appcursos.presentador
import androidx.constraintlayout.motion.widget.KeyPosition
import com.example.appcursos.modelo.DataVerRegistro
import com.example.appcursos.modelo.clsVerRegistrosModelo
import com.example.appcursos.modelo.dataCategoria
import com.example.appcursos.vista.VerRegistroActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class clsVerRegistrosPresentador(private val vista: VerRegistroActivity) {
    private val modelo = clsVerRegistrosModelo() // instanciamos el modelo
    private var listaCarreras: List<dataCategoria> = emptyList() //  guarda la lista de las carreras que se muestr en el spiner
    fun iniciar() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                listaCarreras = modelo.obtenerCarreras() // el modelo trae las carreras desde la bd
                vista.mostrarCarreras(listaCarreras) // muestra las carreras en el spiner

            } catch (e: Exception) {
                vista.mostrarError("error:" + e.localizedMessage) // si algo falla muestra el error
            }
        }
    }
    // esta funciona se ejecuta cuando seleccionamos una carrera en el sipner
    fun seleccionarCarrera(idCarrera: Int) {
        if (idCarrera > 0) {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val listaCursos=modelo.obtenerCursosPorCarrera(idCarrera) //devuelve la lista de cursos
                    val totalInscritos=modelo.calcularTotalInscritos(listaCursos) // devuelve el total de inscritos por curso
                    vista.mostrarTotalInscritos(totalInscritos) // le mandamos la suma ala vista
                    vista.mostrarRegistros(listaCursos) // le mandamos la lista de cursos

                } catch (e: Exception) {
                    vista.mostrarError("Error: " + e.localizedMessage) // muestra el mensaje si hay un error
                }
            }
        } else {
            vista.mostrarTotalInscritos(0) // si elegimos  nada muestra 0 en el total de inscritos
            vista.mostrarRegistros(emptyList()) // una lista vacia si no se elije nada
        }
    }
}