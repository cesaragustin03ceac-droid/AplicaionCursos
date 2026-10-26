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
    private val modelo= clsVerRegistrosModelo()
    private var listaCarreras: List<dataCategoria> = emptyList()
    fun iniciar(){
        CoroutineScope(Dispatchers.Main).launch {
            try{
                listaCarreras=modelo.obtenerCarreras()
                vista.mostrarCarreras(listaCarreras)

            } catch (e: Exception){
                vista.mostrarError("error:"+e.localizedMessage)
            }
        }
    }
    fun seleccionarCarrera(posicion: Int){
        if (posicion>0){
            val carreraSeleccionada=listaCarreras[posicion]
        }
    }
}