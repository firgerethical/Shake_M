package com.example.shakemap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.shakemap.databinding.FragmentConsultasBinding

class ConsultasFragment : Fragment() {

    private var _binding: FragmentConsultasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConsultasBinding.inflate(inflater, container, false)

        binding.btnEnviar.setOnClickListener {
            val ubicacion = binding.etUbicacion.text.toString()
            val intensidad = binding.etIntensidad.text.toString()
            val descripcion = binding.etDescripcion.text.toString()

            if (ubicacion.isBlank() || intensidad.isBlank() || descripcion.isBlank()) {
                Toast.makeText(requireContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {

                //BASE DE DATOS
                binding.tvConfirmacion.visibility = View.VISIBLE
                binding.etUbicacion.text.clear()
                binding.etIntensidad.text.clear()
                binding.etDescripcion.text.clear()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
