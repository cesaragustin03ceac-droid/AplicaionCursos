package com.example.appcursos.presentador

import android.view.View
import com.example.appcursos.vista.RegistroCursoActivity
import com.example.appcursos.modelo.clsRegistroCursoModelo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class clsRegistroCursoPresentador(private val view: RegistroCursoActivity){
    private val modelo=clsRegistroCursoModelo()
    fun realizarRegistro(idCurso:Int,matricula: String, fecha: String,correo: String){
        if(matricula.isEmpty()||fecha.isEmpty()||correo.isEmpty()){
            view.mostrarError("Por favor, llena todos los campos")
            return
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            view.mostrarError("Ingresa un correo electronico valido")
            return
        }
        val regexFecha= Regex("^\\d{4}-\\d{2}-\\d{2}$")
        if (regexFecha.matches(fecha)){
            view.mostrarError("La fecha debe tener formato AAAA-MM-DD")
            return
        }

        CoroutineScope(Dispatchers.Main).launch{
            try {
                val respuesta= withContext(Dispatchers.IO){
                    modelo.registrarAlumno(idCurso,matricula,fecha,correo)
                }
                if(respuesta.exito){
                    view.registroExitoso(respuesta.mensaje)
                }
                else{
                    view.mostrarError(respuesta.mensaje)
                }
            }
            catch (e: Exception){
                view.mostrarError("Error de Conexion: ${e.localizedMessage ?:"Error Desconocido"}")
            }

        }
    }
    fun realizarBusqueda(matricula: String){
        if (matricula.isBlank()){
            view.mostrarError("Por favor, ingrese una matricula")
            return
        }
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta=withContext(Dispatchers.IO){
                    modelo.buscarAlumno(matricula)
                }
                if(respuesta.exito){
                    view.mostrarCorreo(respuesta.correo)
                }else{
                    view.mostrarError(respuesta.mensaje)
                }
            }catch (e: Exception){
                view.mostrarError("Error en conexion: ${e.localizedMessage?:"Error desconocido"}")
            }
        }
    }


}