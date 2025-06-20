package com.example.shakemap

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ContactoAdapter(
    private val context: Context,
    private val lista: List<ContactoEmergencia>
) : RecyclerView.Adapter<ContactoAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.xNombre)
        val telefono: TextView = view.findViewById(R.id.xTelefono)
        val imagen: ImageView = view.findViewById(R.id.xContacto)

        init {
            view.setOnClickListener {
                val contacto = lista[adapterPosition]

                AlertDialog.Builder(context)
                    .setTitle("Selecciona una opcion")
                    .setItems(arrayOf("Llamar")) { _, which ->
                        when (which) {
                            0 -> {
                                try {
                                    val intent = Intent(Intent.ACTION_DIAL).apply {
                                        data = Uri.parse("tel:${contacto.telefono}")
                                    }
                                    context.startActivity(intent)
                                } catch (e: Exception) {
                                    Toast.makeText(context, "Error...", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                    .setNegativeButton("Cancelar", null)
                    .show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(context).inflate(R.layout.item_contacto, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contacto = lista[position]
        holder.nombre.text = contacto.nombre
        holder.telefono.text = contacto.telefono
        holder.imagen.setImageResource(contacto.iconoResId)
    }

    override fun getItemCount(): Int = lista.size
}
