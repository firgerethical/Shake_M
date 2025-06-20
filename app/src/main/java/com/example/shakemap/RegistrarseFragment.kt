package com.example.shakemap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.shakemap.databinding.FragmentRegistrarseBinding

class RegistrarseFragment : Fragment() {
    private var _binding: FragmentRegistrarseBinding? = null
    private val binding get() = _binding!!

    private lateinit var dbHelper: BDHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrarseBinding.inflate(inflater, container, false)
        dbHelper = BDHelper(requireContext())

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

        if (dbHelper.existeUsuario(correo)) {
            Toast.makeText(requireContext(), "Ya existe un usuario con ese correo", Toast.LENGTH_SHORT).show()
            return
        }

        val resultado = dbHelper.insertarUsuario(nombre, correo, pass)

        if (resultado) {
            Toast.makeText(requireContext(), "Registro exitoso", Toast.LENGTH_SHORT).show()
            limpiarCampos()
        } else {
            Toast.makeText(requireContext(), "Error al registrar", Toast.LENGTH_SHORT).show()
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
