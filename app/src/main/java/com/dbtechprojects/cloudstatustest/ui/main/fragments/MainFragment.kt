package com.dbtechprojects.cloudstatustest.ui.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.dbtechprojects.cloudstatustest.R
import com.dbtechprojects.cloudstatustest.databinding.FragmentMainBinding
import com.dbtechprojects.cloudstatustest.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private val mainNavController by lazy {
        findNavController()
    }
    private val mainActivity by lazy {
        requireActivity() as MainActivity
    }
    private val navHostFragment by lazy {
        (childFragmentManager.findFragmentById(binding.childNavHost.id) as NavHostFragment)
    }
    private val childNavController by lazy {
        navHostFragment.navController
    }
    val bottomNavBar by lazy {
        binding.bottomNavigationBar
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        setHasOptionsMenu(true)
        setUpBottomNavigationBar()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, mainNavController)
    }

    private fun setUpBottomNavigationBar() {
        Log.e("TAG", "setup called")
        println(bottomNavBar.id)
        NavigationUI.setupWithNavController(bottomNavBar, childNavController)
    }
}
