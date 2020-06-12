package com.junianto.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.os.postDelayed
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.junianto.notesapp.db.NotesDB
import com.junianto.notesapp.repository.NotesRepository
import com.junianto.notesapp.ui.home.HomeViewModel
import com.junianto.notesapp.ui.home.HomeViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notesRepository = NotesRepository(NotesDB.invoke(this))
        val viewModelProviderFactory = HomeViewModelFactory(notesRepository)
        homeViewModel = ViewModelProvider(this, viewModelProviderFactory).get(HomeViewModel::class.java)

        setupViews()
    }

    fun setupViews() {
        var navHostFrag = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFrag.navController
        NavigationUI.setupWithNavController(bottomNavbar, navHostFrag.navController)

        var appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.reminderFragment))
        setupActionBarWithNavController(navHostFrag.navController, appBarConfiguration)
    }

    private var backPressedOnce = false

    override fun onBackPressed() {
        if (navController.graph.startDestination == navController.currentDestination?.id) {
            if (backPressedOnce) {
                super.onBackPressed()
                return
            }

            backPressedOnce = true
            Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show()

            Handler().postDelayed(2000) {
                backPressedOnce = false
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        var appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.reminderFragment))
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}