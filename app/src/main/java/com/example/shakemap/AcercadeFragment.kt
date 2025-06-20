package com.example.shakemap

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class AcercadeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_acercade, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardMiguel = view.findViewById<CardView>(R.id.cardMiguel)
        cardMiguel.setOnClickListener {
            val url = "https://www.instagram.com/miguel_quake"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        val cardRicardo = view.findViewById<CardView>(R.id.cardRicardo)
        cardRicardo.setOnClickListener {
            val url = "https://www.instagram.com/luxzumbra?igsh=OG5wMTZna2QxM2xi&utm_source=qr"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        val cardAshley = view.findViewById<CardView>(R.id.cardAshley)
        cardAshley.setOnClickListener {
            val url = "https://www.instagram.com/xml.ach?igsh=dnQzejdxdjcxZXJk"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}
