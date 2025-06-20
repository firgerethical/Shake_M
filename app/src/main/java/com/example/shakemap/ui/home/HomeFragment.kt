package com.example.shakemap.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shakemap.R
import com.example.shakemap.databinding.FragmentHomeBinding
import android.graphics.Color

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private fun toRoman(num: Int): String {
        return when (num) {
            1 -> "I"
            2 -> "II"
            3 -> "III"
            4 -> "IV"
            5 -> "V"
            6 -> "VI"
            7 -> "VII"
            8 -> "VIII"
            9 -> "IX"
            10 -> "X"
            else -> ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // (DATOS BD)
        val intensidad = 6.2
        val magnitud = 4.8
        val profundidad = 12.0
        val localizacion = "CDMX"
        val haceTiempo = "Hace 10 minutos"


        val descripcionIntensidad = when (intensidad.toInt()) {
            1 -> "(No sentido)"
            2, 3 -> "(Débil)"
            4 -> "(Ligero)"
            5 -> "(Moderado)"
            6 -> "(Fuerte)"
            7 -> "(Muy Fuerte)"
            8 -> "(Severo)"
            9 -> "(Violento)"
            10 -> "(Extremo)"
            else -> "Sin Lectura"
        }

        // ACTUALIZAR DATOS BD
        val intensidadRomana = toRoman(intensidad.toInt())
        binding.xIntensidad.text = "Intensidad: $intensidadRomana $descripcionIntensidad"
        binding.xMagnitud.text = "Magnitud: $magnitud"
        binding.xProfundidad.text = "Profundidad: $profundidad km"
        binding.xLocalizacion.text = "Localización: $localizacion"
        binding.xHaceTiempo.text = haceTiempo

        val colorHex = when {
            intensidad >= 10.0 -> "#bf3030"
            intensidad >= 9.0 -> "#d8613e"
            intensidad >= 8.0 -> "#d8943e"
            intensidad >= 7.0 -> "#d8b93e"
            intensidad >= 6.0 -> "#dacf26"
            intensidad >= 5.0 -> "#53ca7f"
            intensidad >= 4.0 -> "#9ad6e2"
            intensidad >= 3.0 -> "#64a2ae"
            intensidad >= 2.0 -> "#73abb6"
            intensidad >= 1.0 -> "#9ad6e2"
            else -> "#7eebff"
        }

        binding.cardIntensidad.setCardBackgroundColor(Color.parseColor(colorHex))

        // AGREGAR MAS COMPORTAMIENTOS
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
