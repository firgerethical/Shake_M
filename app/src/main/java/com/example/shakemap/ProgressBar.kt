package com.example.shakemap

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ProgressBar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_bar)

        window.statusBarColor = getColor(R.color.teal_200)

        val imagen = findViewById<ImageView>(R.id.ximagen)
        val texto = findViewById<TextView>(R.id.xcargando)

        Glide.with(this)
            .asGif()
            .load(R.drawable.loader)
            .into(imagen)

        Handler(Looper.getMainLooper()).postDelayed({
            texto.visibility = android.view.View.GONE
            imagen.visibility = android.view.View.GONE

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 5000)
    }
}
