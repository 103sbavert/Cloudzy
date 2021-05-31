package com.dbtechprojects.cloudstatustest.ui.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dbtechprojects.cloudstatustest.R
import com.dbtechprojects.cloudstatustest.databinding.FragmentGcpBinding
import com.dbtechprojects.cloudstatustest.ui.adapters.GcpItemListAdapter
import com.dbtechprojects.cloudstatustest.ui.main.viewmodels.GcpFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GcpFragment : Fragment(R.layout.fragment_gcp) {

    private lateinit var binding: FragmentGcpBinding
    private lateinit var mainFragment: MainFragment
    private val viewModel: GcpFragmentViewModel by viewModels()
    private val gcpItemListAdapter by lazy {
        GcpItemListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGcpBinding.bind(view)
        binding.gcpFeed.adapter = gcpItemListAdapter
        mainFragment = requireParentFragment().requireParentFragment() as MainFragment

        viewModel.gcpEvents.observe(viewLifecycleOwner) {
            gcpItemListAdapter.submitList(it)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainFragment.binding.bottomNavigationBar.setOnNavigationItemReselectedListener {
            viewModel.fetchResults()
            binding.gcpFeed.smoothScrollToPosition(0)
        }
    }
}
