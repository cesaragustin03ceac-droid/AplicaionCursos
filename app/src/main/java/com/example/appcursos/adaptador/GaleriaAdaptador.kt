package com.example.appcursos.adaptador

import android.content.ClipData
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appcursos.R
import com.example.appcursos.modelo.DataImagenesCurso
import com.example.appcursos.vista.DetalleCursoActivity
import com.example.appcursos.vista.RegistroCursoActivity

class GaleriaAdaptador (private var listaImagen: List<DataImagenesCurso>): RecyclerView.Adapter<GaleriaAdaptador.GaleriaViewHolder>(){


    class GaleriaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgGaleria: ImageView =itemView.findViewById(R.id.imgCursos)
        val txtSalon: TextView=itemView.findViewById(R.id.txtSalon)
        val txtCurso : TextView=itemView.findViewById(R.id.txtCurso)
        val btnInscribir: Button = itemView.findViewById(R.id.btnRegistar)
        val btnDetalle: Button = itemView.findViewById(R.id.btnDetalle)
    }
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): GaleriaViewHolder{
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_cursos, parent, false)
        return GaleriaViewHolder(view)
    }

    override fun onBindViewHolder(holder: GaleriaViewHolder, position: Int) {
        val item = listaImagen[position]
        holder.txtCurso.text = item.nombreCurso
        holder.txtSalon.text = "salon:" + item.salon


        val baseUrl = "http://10.173.120.214/Curso_api/"

        val urlCompleta = baseUrl + item.urlImagen
        Glide.with(holder.itemView.context)
            .load(urlCompleta)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(holder.imgGaleria)

        holder.btnInscribir.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, RegistroCursoActivity::class.java).apply {
                putExtra("id_curso", item.id)
                putExtra("nombre_curso", item.nombreCurso)
            }
            context.startActivity(intent)

        }
        holder.btnDetalle.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetalleCursoActivity::class.java).apply {
                putExtra("ID_CURSO", item.id)
            }
            context.startActivity(intent)

        }
    }
    override fun getItemCount(): Int {
        return listaImagen.size
    }

    fun actualizarLista(nuevaLista: List<DataImagenesCurso>){
        listaImagen=nuevaLista
        notifyDataSetChanged()
    }

}