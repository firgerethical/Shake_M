package com.example.shakemap.ui.usuarios

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.shakemap.data.User
import com.example.shakemap.databinding.DialogUsuarioFormBinding

class UsuarioFormDialogFragment(
    private val user: User? = null,
    private val onSubmit: (User) -> Unit,
    private val onDelete: ((User) -> Unit)? = null
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogUsuarioFormBinding.inflate(LayoutInflater.from(context))
        val isEdit = user != null

        if (isEdit) {
            binding.etUsername.setText(user?.username)
            binding.etPassword.setText(user?.password)
            binding.etRole.setText(user?.role)
            binding.etUsername.isEnabled = false
        }

        val builder = AlertDialog.Builder(requireContext())
            .setTitle(if (isEdit) "Editar Usuario" else "Nuevo Usuario")
            .setView(binding.root)
            .setPositiveButton(if (isEdit) "Actualizar" else "Agregar") { _, _ ->
                val updatedUser = user?.copy(
                    password = binding.etPassword.text.toString(),
                    role = binding.etRole.text.toString()
                ) ?: User(
                    username = binding.etUsername.text.toString(),
                    password = binding.etPassword.text.toString(),
                    role = binding.etRole.text.toString()
                )
                onSubmit(updatedUser)
            }
            .setNegativeButton("Cancelar", null)

        if (isEdit && onDelete != null) {
            builder.setNeutralButton("Eliminar") { _, _ -> user?.let { onDelete(it) } }
        }

        return builder.create()
    }
}