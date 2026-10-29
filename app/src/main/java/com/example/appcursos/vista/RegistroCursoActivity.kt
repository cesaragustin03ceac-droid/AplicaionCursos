package com.example.appcursos.vista

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.appcursos.R
import com.example.appcursos.presentador.clsRegistroCursoPresentador

class RegistroCursoActivity : AppCompatActivity() {

    private lateinit var edtMatricula: EditText
    private lateinit var edtFechaRegistro: EditText
    private lateinit var edtCorreo: EditText
    private lateinit var btnConfInscripcion: Button
    private lateinit var btnCancInscripcion: Button
    private lateinit var btnRegresar: ImageView
    private lateinit var imgCurso: ImageView
    private lateinit var presenter: clsRegistroCursoPresentador
    private lateinit var txtEtiqInscripcion: TextView
    private lateinit var txtCursoP: TextView
    private var idCurso: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_curso)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        txtEtiqInscripcion = findViewById(R.id.txtEtiqInscripcion)
        txtCursoP = findViewById(R.id.txtCursoP)
        edtMatricula = findViewById(R.id.edtMatricula)
        edtFechaRegistro = findViewById(R.id.edtFechaRegistro)
        edtCorreo = findViewById(R.id.edtCorreo)
        btnConfInscripcion = findViewById(R.id.btnConfInscripcion)
        btnCancInscripcion = findViewById(R.id.btnCancInscripcion)

        btnRegresar = findViewById<ImageView>(R.id.btnRegresar)
        imgCurso=findViewById<ImageView>(R.id.imgCurso)

        idCurso = intent.getIntExtra("id_curso", 0)
        val nombreCurso = intent.getStringExtra("nombre_curso") ?: ""
        val urlImagen = intent.getStringExtra("url_imagen") ?: ""
        val urlImagenCompleta = "http://192.168.0.114/Curso_api/" + urlImagen
        //"http://192.168.1.67/Curso_api/upload/"
        Glide.with(this)
            .load(urlImagenCompleta)
            .into(imgCurso)

        txtEtiqInscripcion.text = "INSCRIPCIÓN:\n ${nombreCurso.uppercase()}"
        txtCursoP.text = nombreCurso.uppercase()

        presenter = clsRegistroCursoPresentador(this)

        edtMatricula.setOnEditorActionListener(::buscarDatos)

        btnConfInscripcion.setOnClickListener(this::ejecutarRegistroCurso)

        btnCancInscripcion.setOnClickListener {
            finish()
        }

        btnRegresar.setOnClickListener {
            finish()
        }
    }
    fun buscarDatos(t: TextView?, boton: Int, evento: KeyEvent?): Boolean {
        if (boton== EditorInfo.IME_ACTION_DONE){
            presenter.realizarBusqueda(edtMatricula.text.toString().trim())
        }
        return false
    }
    fun mostrarCorreo(correo: String){
        edtCorreo.setText(correo)
    }

    fun ejecutarRegistroCurso(v: View) {
        val matricula = edtMatricula.text.toString().trim()
        val fecha = edtFechaRegistro.text.toString().trim()
        val correo = edtCorreo.text.toString().trim()

        presenter.realizarRegistro(idCurso, matricula, fecha, correo)
    }

    fun registroExitoso(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        finish()
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}