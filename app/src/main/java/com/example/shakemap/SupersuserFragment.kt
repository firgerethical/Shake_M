package com.example.shakemap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shakemap.databinding.FragmentSupersuserBinding

class SuperuserFragment : Fragment() {
    private var _binding: FragmentSupersuserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSupersuserBinding.inflate(inflater, container, false)

        binding.btnUsuarios.setOnClickListener {
            findNavController().navigate(R.id.action_superuserFragment_to_usuariosFragment)
        }

        binding.btnConsultas.setOnClickListener {
            findNavController().navigate(R.id.action_superuserFragment_to_consultasFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}