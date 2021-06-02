package com.dbtechprojects.cloudstatustest.ui.main.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.dbtechprojects.cloudstatustest.R
import com.dbtechprojects.cloudstatustest.databinding.FragmentMainBinding
import com.dbtechprojects.cloudstatustest.ui.main.MainActivity
import com.dbtechprojects.cloudstatustest.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    lateinit var binding: FragmentMainBinding
    private lateinit var mainNavController: NavController
    private lateinit var mainActivity: MainActivity
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var childNavController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)
        mainNavController = findNavController()
        mainActivity = requireActivity() as MainActivity
        navHostFragment = childFragmentManager.findFragmentById(binding.childNavHost.id) as NavHostFragment
        childNavController = navHostFragment.navController

        setHasOptionsMenu(true)

        NavigationUI.setupWithNavController(binding.bottomNavigationBar, childNavController)
        when (mainActivity.intent.action) {
            Constants.AWS_INTENT_ACTION -> childNavController.navigate(R.id.aws_fragment)
            Constants.GCP_INTENT_ACTION -> childNavController.navigate(R.id.gcp_fragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, mainNavController)
    }
}
