package com.example.shakemap.ui.usuarios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shakemap.data.AppDatabase
import com.example.shakemap.data.User
import com.example.shakemap.databinding.FragmentUsuariosBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsuariosFragment : Fragment() {
    private var _binding: FragmentUsuariosBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsuariosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UserAdapter(emptyList()) { user ->
            showUserDialog(user)
        }
        binding.recyclerViewUsuarios.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewUsuarios.adapter = adapter

        binding.fabAddUser.setOnClickListener {
            showUserDialog(null)
        }

        loadUsers()
    }

    private fun loadUsers() {
        lifecycleScope.launch(Dispatchers.IO) {
            val userDao = AppDatabase.getDatabase(requireContext()).userDao()
            val users = userDao.getAllUsers()
            withContext(Dispatchers.Main) {
                adapter.updateUsers(users)
            }
        }
    }

    private fun showUserDialog(user: User?) {
        UsuarioFormDialogFragment(
            user = user,
            onSubmit = { updatedUser ->
                lifecycleScope.launch(Dispatchers.IO) {
                    val userDao = AppDatabase.getDatabase(requireContext()).userDao()
                    if (user == null) {
                        userDao.insertUser(updatedUser)
                        withContext(Dispatchers.Main) {
                            Toast.makeText(requireContext(), "Usuario agregado", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        userDao.updateUser(updatedUser)
                        withContext(Dispatchers.Main) {
                            Toast.makeText(requireContext(), "Usuario actualizado", Toast.LENGTH_SHORT).show()
                        }
                    }
                    loadUsers()
                }
            },
            onDelete = { userToDelete ->
                lifecycleScope.launch(Dispatchers.IO) {
                    val userDao = AppDatabase.getDatabase(requireContext()).userDao()
                    userDao.deleteUser(userToDelete)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "Usuario eliminado", Toast.LENGTH_SHORT).show()
                    }
                    loadUsers()
                }
            }
        ).show(parentFragmentManager, "UsuarioFormDialog")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}