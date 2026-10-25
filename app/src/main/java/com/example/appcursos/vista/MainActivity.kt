package com.example.appcursos.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appcursos.R
import com.example.appcursos.adaptador.GaleriaAdaptador
import com.example.appcursos.modelo.DataImagenesCurso
import com.example.appcursos.modelo.dataCategoria
import com.example.appcursos.presentador.clsCarreraPresentador

class MainActivity : AppCompatActivity() {
    private lateinit var presenter : clsCarreraPresentador
    private lateinit var spinnerCategoria: Spinner
    private lateinit var recyclerCursos: RecyclerView
    private lateinit var adapterGaleria: GaleriaAdaptador
    private lateinit var btnVerRegistros: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        presenter= clsCarreraPresentador(this)
        spinnerCategoria= findViewById(R.id.spnCategorias)
        recyclerCursos=findViewById(R.id.rvLista)
        btnVerRegistros = findViewById(R.id.btnVerRegistros)

        recyclerCursos.layoutManager=androidx.recyclerview.widget.GridLayoutManager(this, 2)
        adapterGaleria= GaleriaAdaptador(emptyList())
        recyclerCursos.adapter=adapterGaleria
        configurarListeners()
        presenter.inciar()
    }
    private fun configurarListeners(){
        spinnerCategoria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                presenter.seleccionarCarrera(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        btnVerRegistros.setOnClickListener {
            val intent = Intent(this, VerRegistroActivity::class.java)
            startActivity(intent)
        }
    }
    fun mostrarCategorias(categoria: List<dataCategoria>){
        val adapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,categoria)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategoria.adapter=adapter
    }
    fun mostrarCursos(cursos: List<DataImagenesCurso>){
        adapterGaleria.actualizarLista(cursos)
    }

    fun mostrarMensajeExito(id: Int,nombre: String){
        Toast.makeText(this,"ID"+id.toString()+ "-Seleccionaste" + nombre, Toast.LENGTH_SHORT).show()
    }

    fun mostrarError(mensaje: String){
        Toast.makeText(this,mensaje, Toast.LENGTH_LONG).show()
    }

}