package com.example.shakemap

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.shakemap.data.AppDatabase
import com.example.shakemap.data.User
import com.example.shakemap.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        GlobalScope.launch(Dispatchers.IO) {
            val userDao = AppDatabase.getDatabase(applicationContext).userDao()
            if (userDao.countUsers() == 0) {
                val testUsers = listOf(
                    User(username = "Raul", password = "test123", role = "periodista"),
                    User(username = "Ximena", password = "test123", role = "periodista"),
                    User(username = "Mariela", password = "test123", role = "user"),
                    User(username = "Lydia", password = "test123", role = "user"),
                    User(username = "Roberto", password = "test123", role = "user"),
                    User(username = "Pedro", password = "test123", role = "user"),
                    User(username = "Jos", password = "test123", role = "user"),
                    User(username = "Miguel", password = "test123", role = "user"),
                    User(username = "user9", password = "test123", role = "user"),
                    User(username = "user10", password = "test123", role = "user"),
                    User(username = "admin", password = "admin123", role = "admin")
                )
                testUsers.forEach { userDao.insertUser(it) }
            }
        }

        // Configura la toolbar
        setSupportActionBar(binding.appBarMain.toolbar)

        // Acción del FAB (icono flotante)
        binding.appBarMain.fab.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://x.com/Sismoalertamex")
            startActivity(intent)
        }

        // Navigation Drawer y Navigation Controller setup
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_consultas,
                R.id.nav_noticias,
                R.id.nav_protocolos,
                R.id.nav_contactos,
                R.id.nav_acercade,
                R.id.superuserFragment,
                R.id.nav_registrarse,
                R.id.usuariosFragment,
                R.id.loginFragment
            ),
            drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.itemIconTintList = null
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}