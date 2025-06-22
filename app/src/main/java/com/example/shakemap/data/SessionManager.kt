package com.example.shakemap.data

import android.content.Context

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("shake_map_prefs", Context.MODE_PRIVATE)

    fun saveSession(username: String, role: String) {
        prefs.edit().putString("username", username).putString("role", role).apply()
    }

    fun getRole(): String? = prefs.getString("role", null)
    fun getUsername(): String? = prefs.getString("username", null)
    fun clearSession() = prefs.edit().clear().apply()
}