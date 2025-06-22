package com.example.shakemap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.shakemap.data.SessionManager
import com.example.shakemap.databinding.FragmentLoginBinding
import com.example.shakemap.ui.UserViewModel
import com.example.shakemap.ui.UserViewModelFactory

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(requireActivity().application)
    }

    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        sessionManager = SessionManager(requireContext())

        binding.xLogin.setOnClickListener {
            loginUsuario()
        }

        return binding.root
    }

    private fun loginUsuario() {
        val username = binding.xUsuario.text.toString().trim()
        val password = binding.xContrasena.text.toString()

        if (username.isEmpty() || password.isEmpty()) {
            showError("Por favor ingresa usuario y contraseña")
            return
        }

        userViewModel.getUserByUsername(username) { user ->
            if (user == null || user.password != password) {
                showError("Usuario o contraseña incorrectos")
            } else {
                // Guardar sesión
                sessionManager.saveSession(user.username, user.role)
                Toast.makeText(requireContext(), "Bienvenido ${user.username}", Toast.LENGTH_SHORT).show()
                // Redirección según rol
                if (user.role == "admin") {
                    findNavController().navigate(R.id.action_loginFragment_to_superuserFragment)
                } else {
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
            }
        }
    }

    private fun showError(msg: String) {
        binding.tvError.text = msg
        binding.tvError.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}