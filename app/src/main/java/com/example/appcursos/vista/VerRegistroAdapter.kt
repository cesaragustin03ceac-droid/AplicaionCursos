package com.example.appcursos.vista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView
import com.example.appcursos.R
import com.example.appcursos.modelo.DataVerRegistro
import com.example.appcursos.modelo.RetrofitCliente

class VerRegistroAdapter(private var listaRegistros: List<DataVerRegistro> = emptyList()) : RecyclerView.Adapter<VerRegistroAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNombreCurso: TextView = itemView.findViewById(R.id.txtNombreCurso)
        val txtCantidadInscritos: TextView = itemView.findViewById(R.id.txtCantidadInscritos)
        val imgCurso: ImageView = itemView.findViewById(R.id.imgCurso)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ver_registro, parent, false)
        return ViewHolder(vista)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val registro = listaRegistros[position]
        holder.txtNombreCurso.text = registro.nombreCurso
        holder.txtCantidadInscritos.text ="inscritos:${registro.cantidadInscritos}/cupos:${registro.totalCupos}"
        val urlImagenCompleta = "http://192.168.0.114/Curso_api/uploads/" + registro.urlImagen
        //"http://192.168.1.67/Curso_api/upload/"

        Glide.with(holder.itemView.context)
            .load(urlImagenCompleta)
            .into(holder.imgCurso)
    }
    override fun getItemCount(): Int {
        return listaRegistros.size
    }
    fun actualizarLista(nuevaLista: List<DataVerRegistro>) {
        listaRegistros = nuevaLista
        notifyDataSetChanged()
    }
}