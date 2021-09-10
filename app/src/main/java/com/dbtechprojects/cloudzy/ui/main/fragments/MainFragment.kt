package com.dbtechprojects.cloudzy.ui.main.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.dbtechprojects.cloudzy.R
import com.dbtechprojects.cloudzy.databinding.FragmentMainBinding
import com.dbtechprojects.cloudzy.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    lateinit var binding: FragmentMainBinding
    private lateinit var parentNavController: NavController
    private lateinit var parentActivity: FragmentActivity
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var childNavController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)
        parentNavController = findNavController()
        parentActivity = requireActivity()
        navHostFragment = childFragmentManager.findFragmentById(binding.childNavHost.id) as NavHostFragment
        childNavController = navHostFragment.navController

        setHasOptionsMenu(true)

        NavigationUI.setupWithNavController(binding.bottomNavigationBar, childNavController)

        when (parentActivity.intent.action) {
            Constants.AWS_INTENT_ACTION -> childNavController.navigate(R.id.aws_fragment)
            Constants.GCP_INTENT_ACTION -> childNavController.navigate(R.id.gcp_fragment)
            Constants.AZURE_INTENT_ACTION -> childNavController.navigate(R.id.gcp_fragment)
        }

        binding.bottomNavigationBar.setOnItemReselectedListener {
            (navHostFragment.childFragmentManager.fragments[0] as ScrollableFragment).scrollToTop()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, parentNavController)
    }
}
