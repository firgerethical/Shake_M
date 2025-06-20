package com.example.shakemap

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProtocolosFragment : Fragment() {

    private lateinit var contenedorInstrucciones: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_protocolos, container, false)

        val btnVideo = view.findViewById<Button>(R.id.xVerVideo)
        val btnCasa = view.findViewById<Button>(R.id.xCasa)
        val btnEscuela = view.findViewById<Button>(R.id.xEscuela)
        val btnTransporte = view.findViewById<Button>(R.id.xTransporte)
        val btnCalle = view.findViewById<Button>(R.id.xCalle)
        contenedorInstrucciones = view.findViewById(R.id.xInstrucciones)

        btnVideo.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/shorts/db9adYI1kwE?feature=share"))
            startActivity(intent)
        }

        btnCasa.setOnClickListener {
            mostrarInstrucciones(
                listOf(R.drawable.paso1, R.drawable.paso2, R.drawable.paso3),
                listOf(
                    "Aléjate de ventanas.",
                    "Apaga el gas.",
                    "No uses elevadores."
                )
            )
        }

        btnEscuela.setOnClickListener {
            mostrarInstrucciones(
                listOf(R.drawable.paso4, R.drawable.paso5),
                listOf(
                    "Ubica la zona de seguridad.",
                    "Sigue las indicaciones del personal."
                )
            )
        }

        btnTransporte.setOnClickListener {
            mostrarInstrucciones(
                listOf(R.drawable.paso6, R.drawable.paso7, R.drawable.paso8),
                listOf(
                    "Mantén la calma.",
                    "Sujétate bien.",
                    "No intentes bajarte."
                )
            )
        }

        btnCalle.setOnClickListener {
            mostrarInstrucciones(
                listOf(R.drawable.paso9, R.drawable.paso10),
                listOf(
                    "Aléjate de postes y cables.",
                    "Mantente lejos de edificios y vidrios."
                )
            )
        }

        return view
    }

    private fun mostrarInstrucciones(imagenes: List<Int>, textos: List<String>) {
        contenedorInstrucciones.removeAllViews()
        contenedorInstrucciones.visibility = View.VISIBLE

        val context = requireContext()

        for (i in imagenes.indices) {
            val item = LinearLayout(context).apply {
                orientation = LinearLayout.HORIZONTAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { bottomMargin = 16 }
            }

            val imagen = ImageView(context).apply {
                setImageResource(imagenes[i])
                layoutParams = LinearLayout.LayoutParams(150, 150).apply {
                    marginEnd = 16
                }
                scaleType = ImageView.ScaleType.CENTER_CROP
            }

            val texto = TextView(context).apply {
                text = textos[i]
                textSize = 20f
                setTextColor(Color.BLACK)
                layoutParams = LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
                )
            }

            item.addView(imagen)
            item.addView(texto)
            contenedorInstrucciones.addView(item)
        }
    }
}
