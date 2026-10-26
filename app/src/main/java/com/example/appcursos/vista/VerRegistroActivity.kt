package com.example.appcursos.vista

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcursos.R
import com.example.appcursos.modelo.DataVerRegistro
import com.example.appcursos.modelo.dataCategoria
import com.example.appcursos.presentador.clsVerRegistrosPresentador

class VerRegistroActivity : AppCompatActivity() {
    private lateinit var spnCarreras: Spinner
    private lateinit var txtTotal: TextView
    private lateinit var rvRegistros: RecyclerView
    private lateinit var btnVolver: Button
    // instanciamos nuestro presentador
    private val presentador = clsVerRegistrosPresentador(this)
    // declaramos nuestro adaptador dibuja las tarjetas en el reclicler view
    private lateinit var  adapter: VerRegistroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_registro)
        // enlazamos con los id
        spnCarreras = findViewById(R.id.spnCarreras)
        txtTotal = findViewById(R.id.txtTotal)
        rvRegistros = findViewById(R.id.rvRegistros)
        btnVolver = findViewById(R.id.btnVolver)

        adapter=VerRegistroAdapter() // inicializa el adaptador
        rvRegistros.adapter = adapter // le asignamos el adaptador al recicler view
        rvRegistros.layoutManager = LinearLayoutManager(this) // acomoda las tajetas en vetical
        configurarListeners()
        presentador.iniciar()
    }
    private fun configurarListeners() {
        btnVolver.setOnClickListener { finish() } // regresa ala pantalla principal
        spnCarreras.onItemSelectedListener = object : AdapterView.OnItemSelectedListener { // despliega la lista del spiner
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // la carrera que seleccionamos en el spiner
                val carreraSeleccionada = parent?.getItemAtPosition(position) as? dataCategoria
                // si la carrera existe enviamos el id al presentador
                if (carreraSeleccionada != null) {
                    presentador.seleccionarCarrera(carreraSeleccionada.id)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    // recibe la lista de carreras y llena el spiner
    fun mostrarCarreras(listaCarreras: List<dataCategoria>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaCarreras)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnCarreras.adapter = adapter
    }
    // recibe los cursos por carrera
    fun mostrarRegistros(lista: List<DataVerRegistro>) {
        adapter.actualizarLista(lista)
    }
    // mensaje de error
    fun mostrarError(mensaje: String) { // funcion para el mensaje de error
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
    // recibe la suma de el total de inscritos por curso
    fun mostrarTotalInscritos(total: Int) {
        txtTotal.text = "Total de inscritos:$total"
    }

}