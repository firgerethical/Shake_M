package com.example.shakemap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shakemap.databinding.FragmentNoticiasBinding

class NoticiasFragment : Fragment() {

    private var _binding: FragmentNoticiasBinding? = null
    private val binding get() = _binding!!

    private lateinit var noticiasAdapter: NoticiasAdapter
    private val listaNoticias = mutableListOf<NoticiasModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoticiasBinding.inflate(inflater, container, false)

        noticiasAdapter = NoticiasAdapter(listaNoticias)
        binding.xNoticias.layoutManager = LinearLayoutManager(requireContext())
        binding.xNoticias.adapter = noticiasAdapter

        // SIMULAR DATOS
        cargarNoticiasDePrueba()

        return binding.root
    }

    private fun cargarNoticiasDePrueba() {
        listaNoticias.add(
            NoticiasModel(
                "Sismo moderado sacude el centro del país",
                "10 de junio de 2025",
                "Un movimiento telúrico de magnitud 5.2 se registró esta madrugada..."
            )
        )
        listaNoticias.add(
            NoticiasModel(
                "Se actualiza protocolo de seguridad escolar",
                "09 de junio de 2025",
                "La Secretaría de Educación implementará nuevos simulacros..."
            )
        )
        listaNoticias.add(
            NoticiasModel(
                "Nuevas tecnologías para alerta sísmica",
                "08 de junio de 2025",
                "Investigadores anuncian mejoras en la detección temprana..."
            )
        )
        noticiasAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
