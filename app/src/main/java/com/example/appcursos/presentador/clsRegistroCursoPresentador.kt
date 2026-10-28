package com.example.appcursos.presentador

import com.example.appcursos.modelo.clsRegistroCursoModelo
import com.example.appcursos.vista.RegistroCursoActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class clsRegistroCursoPresentador(private val view: RegistroCursoActivity) {

    private val modelo = clsRegistroCursoModelo()

    fun realizarRegistro(idCurso: Int, matricula: String, fecha: String, correo: String) {
        if (matricula.isEmpty() || fecha.isEmpty() || correo.isEmpty()) {
            view.mostrarError("Por favor, llena todos los campos")
            return
        }

        if (matricula.isEmpty() || fecha.isEmpty() || correo.isEmpty()) {
            view.mostrarError("Por favor, llena todos los campos")
            return
        }
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = withContext(Dispatchers.IO) {
                    modelo.registrarAlumno(idCurso, matricula, fecha, correo)
                }
                if (respuesta.exito) {
                    view.registroExitoso(respuesta.mensaje)
                } else {
                    view.mostrarError(respuesta.mensaje)
                }
            } catch (e: Exception) {
                view.mostrarError("Error de conexión: ${e.localizedMessage ?: "Error desconocido"}")
            }
        }
    }
}

