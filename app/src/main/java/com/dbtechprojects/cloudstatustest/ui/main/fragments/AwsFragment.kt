package com.dbtechprojects.cloudstatustest.ui.main.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dbtechprojects.cloudstatustest.R
import com.dbtechprojects.cloudstatustest.databinding.FragmentAwsBinding
import com.dbtechprojects.cloudstatustest.ui.adapters.AwsItemListAdapter
import com.dbtechprojects.cloudstatustest.ui.main.MainActivity
import com.dbtechprojects.cloudstatustest.ui.main.viewmodels.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AwsFragment : Fragment(R.layout.fragment_aws) {
    private lateinit var binding: FragmentAwsBinding
    private val mainActivity: MainActivity by lazy {
        requireActivity() as MainActivity
    }
    private val viewModel: MainFragmentViewModel by viewModels()
    private var awsItemListAdapter = AwsItemListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAwsBinding.bind(view)
        binding.awsFeed.adapter = awsItemListAdapter

        viewModel.awsEvents.observe(viewLifecycleOwner) {
            awsItemListAdapter.submitList(it)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainActivity.bottomNavigationView.setOnNavigationItemReselectedListener {
            viewModel.fetchResults()
        }
    }
}
