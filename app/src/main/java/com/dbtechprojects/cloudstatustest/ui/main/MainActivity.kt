package com.dbtechprojects.cloudstatustest.ui.main

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
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
    private val navHostFragment by lazy {
        (supportFragmentManager.findFragmentById(mainActivityBinding.parentNavHost.id) as NavHostFragment)
    }
    private val navController by lazy {
        navHostFragment.navController
    }
    private val appBarConfiguration by lazy {
        AppBarConfiguration(setOf(R.id.aws_fragment, R.id.gcp_fragment, R.id.azure_fragment))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainActivityBinding.root)
        setSupportActionBar(mainActivityBinding.materialToolbar)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
