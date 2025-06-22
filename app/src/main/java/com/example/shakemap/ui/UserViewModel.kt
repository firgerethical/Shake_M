package com.example.shakemap.ui

import android.app.Application
import androidx.lifecycle.*
import com.example.shakemap.data.AppDatabase
import com.example.shakemap.data.User
import com.example.shakemap.data.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    fun insert(user: User, onResult: (Boolean) -> Unit) = viewModelScope.launch {
        try {
            repository.insertUser(user)
            onResult(true)
        } catch (e: Exception) {
            onResult(false)
        }
    }

    fun getUserByUsername(username: String, onResult: (User?) -> Unit) = viewModelScope.launch {
        onResult(repository.getUserByUsername(username))
    }

    fun getAllUsers(onResult: (List<User>) -> Unit) = viewModelScope.launch {
        onResult(repository.getAllUsers())
    }

    fun updateUser(user: User, onResult: (Boolean) -> Unit) = viewModelScope.launch {
        try {
            repository.updateUser(user)
            onResult(true)
        } catch (e: Exception) {
            onResult(false)
        }
    }

    fun deleteUser(user: User, onResult: (Boolean) -> Unit) = viewModelScope.launch {
        try {
            repository.deleteUser(user)
            onResult(true)
        } catch (e: Exception) {
            onResult(false)
        }
    }
}