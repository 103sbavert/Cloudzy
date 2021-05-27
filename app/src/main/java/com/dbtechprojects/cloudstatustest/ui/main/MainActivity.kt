package com.dbtechprojects.cloudstatustest.ui.main

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.dbtechprojects.cloudstatustest.R
import com.dbtechprojects.cloudstatustest.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences

    private val mainActivityBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var bottomNavigationView: BottomNavigationView

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(mainActivityBinding.root)
        bottomNavigationView = mainActivityBinding.mainBottomNavigation

        val navController = findNavController(R.id.nav_host_fragment)

        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        // setup action bar
        setSupportActionBar(mainActivityBinding.toolbar)

    }

    // progressbarToggle
    fun showProgressbar(isVisible: Boolean) {
        mainActivityBinding.mainProgressBar.isVisible = isVisible
    }


    // setup menu

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    // code to be run when settings button is clicked on toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            // navigate to settings fragment
            val navController = findNavController(R.id.nav_host_fragment)
            navController.navigate(R.id.settingsFragment)

        }

        return super.onOptionsItemSelected(item)
    }
}
