package com.example.shakemap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.shakemap.databinding.FragmentSuperuserBinding

class SuperuserFragment : Fragment() {

    private var _binding: FragmentSuperuserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuperuserBinding.inflate(inflater, container, false)

        binding.xLogin.setOnClickListener {
            val usuario = binding.xUsuario.text.toString()
            val contrasena = binding.xContrasena.text.toString()

            if (usuario.isBlank() || contrasena.isBlank()) {
                binding.tvError.visibility = View.VISIBLE
                binding.tvError.text = "Completa todos los campos"
            } else {
                //cambiar
                binding.tvError.visibility = View.GONE
                Toast.makeText(requireContext(), "Inicio exitoso (falso)", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
