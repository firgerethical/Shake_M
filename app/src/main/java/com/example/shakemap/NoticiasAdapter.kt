package com.example.shakemap

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shakemap.databinding.ItemNoticiasBinding

class NoticiasAdapter(private val noticias: List<NoticiasModel>) :
    RecyclerView.Adapter<NoticiasAdapter.NoticiasViewHolder>() {

    inner class NoticiasViewHolder(val binding: ItemNoticiasBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiasViewHolder {
        val binding = ItemNoticiasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticiasViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticiasViewHolder, position: Int) {
        val noticias = noticias[position]
        holder.binding.xTitulo.text = noticias.titulo
        holder.binding.xFecha.text = noticias.fecha
        holder.binding.xDescripcion.text = noticias.descripcion
    }

    override fun getItemCount(): Int = noticias.size
}
