package com.example.appcursos.vista

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appcursos.R
import com.example.appcursos.modelo.dataCursos
import com.example.appcursos.presentador.clsDetalleCursoPresentador
import com.bumptech.glide.Glide
import com.example.appcursos.modelo.RetrofitCliente

class DetalleCursoActivity : AppCompatActivity() {
    private lateinit var presenter: clsDetalleCursoPresentador
    private lateinit var imgCurso: ImageView
    private lateinit var btnRegresar: Button
    private lateinit var txtNombre: TextView
    private lateinit var txtCodigo: TextView
    private lateinit var txtDescripcion: TextView
    private lateinit var txtEspacio: TextView
    private lateinit var txtHoras: TextView
    private lateinit var txtHorario: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_curso)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        imgCurso = findViewById(R.id.imgCurso)
        btnRegresar = findViewById(R.id.btnRegresar)
        txtNombre = findViewById(R.id.txtdetalleCurso)
        txtCodigo = findViewById(R.id.txtdetalleCodigo)
        txtDescripcion = findViewById(R.id.txtdetalleDescripcionMas)
        txtEspacio = findViewById(R.id.txtdetalleEspacioMas)
        txtHoras = findViewById(R.id.txtdetalleHorasTotales)
        txtHorario = findViewById(R.id.txtdetalleHorario)
        presenter = clsDetalleCursoPresentador(this)
        btnRegresar.setOnClickListener {
            finish()
        }
        val id = intent.getIntExtra("ID_CURSO", 0)
        if (id != 0) {
            presenter.cargarDetalleCurso(id)
        }



    }
    fun mostrarCurso(curso: dataCursos) {
        txtNombre.text = curso.nombreCurso
        txtCodigo.text = "Código: ${curso.codigo}"
        txtDescripcion.text = curso.descripcion
        txtEspacio.text = curso.salon
        txtHoras.text = curso.totalHoras
        txtHorario.text = curso.horario
        Glide.with(this)
            .load(RetrofitCliente.BASE_URL + curso.urlImagen)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_gallery)
            .into(imgCurso)

    }


    fun mostrarError(mensaje: String){
        Toast.makeText(this,mensaje, Toast.LENGTH_LONG).show()
    }

}