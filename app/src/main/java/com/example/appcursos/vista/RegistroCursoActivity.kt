package com.example.appcursos.vista

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appcursos.R
import com.example.appcursos.presentador.clsRegistroCursoPresentador

class RegistroCursoActivity : AppCompatActivity() {

    private lateinit var edtMatricula: EditText
    private lateinit var edtFechaRegistro: EditText
    private lateinit var edtCorreo: EditText
    private lateinit var btnConfInscripcion: Button
    private lateinit var btnCancInscripcion: Button
    private lateinit var btnRegresar: ImageView
    private lateinit var presenter: clsRegistroCursoPresentador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_curso)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtMatricula = findViewById(R.id.edtMatricula)
        edtFechaRegistro = findViewById(R.id.edtFechaRegistro)
        edtCorreo = findViewById(R.id.edtCorreo)
        btnConfInscripcion = findViewById(R.id.btnConfInscripcion)
        btnCancInscripcion = findViewById(R.id.btnCancInscripcion)
        btnRegresar = findViewById(R.id.btnRegresar)

        presenter = clsRegistroCursoPresentador(this)

        btnConfInscripcion.setOnClickListener(this::ejecutarRegistroCurso)

        btnCancInscripcion.setOnClickListener {
            finish()
        }

        btnRegresar.setOnClickListener {
            finish()
        }
    }

    fun ejecutarRegistroCurso(v: View) {
        val matricula = edtMatricula.text.toString().trim()
        val fecha = edtFechaRegistro.text.toString().trim()
        val correo = edtCorreo.text.toString().trim()

        presenter.realizarRegistro(matricula, fecha, correo)
    }

    fun registroExitoso(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        finish()
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}