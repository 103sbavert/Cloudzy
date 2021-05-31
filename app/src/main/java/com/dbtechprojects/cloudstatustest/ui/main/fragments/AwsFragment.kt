package com.dbtechprojects.cloudstatustest.ui.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dbtechprojects.cloudstatustest.R
import com.dbtechprojects.cloudstatustest.databinding.FragmentAwsBinding
import com.dbtechprojects.cloudstatustest.ui.adapters.AwsItemListAdapter
import com.dbtechprojects.cloudstatustest.ui.main.viewmodels.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class AwsFragment : Fragment(R.layout.fragment_aws) {

    private lateinit var binding: FragmentAwsBinding
    private lateinit var mainFragment: MainFragment
    private val viewModel: MainFragmentViewModel by viewModels()
    private val feedListAdapter by lazy {
        AwsItemListAdapter()
    }
    private var shouldNotifyAboutNewItems by Delegates.notNull<Boolean>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAwsBinding.bind(view)
        binding.awsFeed.adapter = feedListAdapter
        mainFragment = requireParentFragment().requireParentFragment() as MainFragment
        shouldNotifyAboutNewItems = false


        viewModel.apiFetchResult.observe(viewLifecycleOwner) {
            Log.e("AWSFragment", "onViewCreated: $it")
        }
        viewModel.feed.observe(viewLifecycleOwner) {
            feedListAdapter.submitList(it)
            if (shouldNotifyAboutNewItems) {
                Toast.makeText(requireContext(), "New Results Found", Toast.LENGTH_SHORT).show()
            }
            shouldNotifyAboutNewItems = true
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainFragment.binding.bottomNavigationBar.setOnNavigationItemReselectedListener {
            viewModel.fetchResults()
            binding.awsFeed.smoothScrollToPosition(0)
        }
    }
}
