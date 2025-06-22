package com.example.shakemap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.shakemap.databinding.FragmentRegistrarseBinding
import com.example.shakemap.data.User
import com.example.shakemap.ui.UserViewModel
import com.example.shakemap.ui.UserViewModelFactory

class RegistrarseFragment : Fragment() {
    private var _binding: FragmentRegistrarseBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrarseBinding.inflate(inflater, container, false)

        binding.xRegistar.setOnClickListener {
            registrarUsuario()
        }

        return binding.root
    }

    private fun registrarUsuario() {
        val nombre = binding.xName.text.toString().trim()
        val correo = binding.xEmail.text.toString().trim()
        val pass = binding.xContra.text.toString()
        val passConfirm = binding.xConfirmContra.text.toString()

        if (nombre.isEmpty() || correo.isEmpty() || pass.isEmpty() || passConfirm.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != passConfirm) {
            Toast.makeText(requireContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }

        userViewModel.getUserByUsername(correo) { user ->
            if (user != null) {
                Toast.makeText(requireContext(), "Ya existe un usuario con ese correo", Toast.LENGTH_SHORT).show()
            } else {
                val nuevoUser = User(
                    username = correo,
                    password = pass, //
                    role = "user"
                )
                userViewModel.insert(nuevoUser) { ok ->
                    if (ok) {
                        Toast.makeText(requireContext(), "Registro exitoso", Toast.LENGTH_SHORT).show()
                        limpiarCampos()
                    } else {
                        Toast.makeText(requireContext(), "Error al registrar", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun limpiarCampos() {
        binding.xName.setText("")
        binding.xEmail.setText("")
        binding.xContra.setText("")
        binding.xConfirmContra.setText("")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}