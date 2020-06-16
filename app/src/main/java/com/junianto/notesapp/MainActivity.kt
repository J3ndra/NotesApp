package com.junianto.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.os.postDelayed
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.junianto.notesapp.data.source.local.NotesDatabase
import com.junianto.notesapp.data.source.repository.NotesRepository
import com.junianto.notesapp.ui.home.HomeViewModel
import com.junianto.notesapp.ui.home.HomeViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notesRepository =
            NotesRepository(
                NotesDatabase.invoke(this)
            )
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