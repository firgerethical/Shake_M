package com.example.shakemap

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDHelper(context: Context) : SQLiteOpenHelper(context, "ShakeMapDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                correo TEXT NOT NULL UNIQUE,
                contrasena TEXT NOT NULL
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)
    }

    fun insertarUsuario(nombre: String, correo: String, contrasena: String): Boolean {
        val db = this.writableDatabase
        val valores = ContentValues().apply {
            put("nombre", nombre)
            put("correo", correo)
            put("contrasena", contrasena)
        }
        val resultado = db.insert("usuarios", null, valores)
        return resultado != -1L
    }

    fun existeUsuario(correo: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM usuarios WHERE correo = ?", arrayOf(correo))
        val existe = cursor.count > 0
        cursor.close()
        return existe
    }
}
