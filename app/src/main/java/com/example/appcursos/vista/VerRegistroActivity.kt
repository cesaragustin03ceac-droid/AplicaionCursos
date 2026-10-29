package com.example.appcursos.vista
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
    private val presentador = clsVerRegistrosPresentador(this)
    private lateinit var  adapter: VerRegistroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_registro)
        spnCarreras = findViewById(R.id.spnCarreras)
        txtTotal = findViewById(R.id.txtTotal)
        rvRegistros = findViewById(R.id.rvRegistros)
        btnVolver = findViewById(R.id.btnVolver)

        adapter=VerRegistroAdapter()
        rvRegistros.adapter = adapter
        rvRegistros.layoutManager = LinearLayoutManager(this)
        configurarListeners()
        presentador.iniciar()
    }
    private fun configurarListeners() {
        btnVolver.setOnClickListener { finish() }
        spnCarreras.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val carreraSeleccionada = parent?.getItemAtPosition(position) as? dataCategoria
                if (carreraSeleccionada != null) {
                    presentador.seleccionarCarrera(carreraSeleccionada.id)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    fun mostrarCarreras(listaCarreras: List<dataCategoria>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaCarreras)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnCarreras.adapter = adapter
    }
    fun mostrarRegistros(lista: List<DataVerRegistro>) {
        adapter.actualizarLista(lista)
    }
    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
    fun mostrarTotalInscritos(total: Int) {
        txtTotal.text = "Total de inscritos:$total"
    }
}