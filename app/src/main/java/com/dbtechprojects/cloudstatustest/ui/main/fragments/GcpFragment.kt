package com.dbtechprojects.cloudstatustest.ui.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dbtechprojects.cloudstatustest.R
import com.dbtechprojects.cloudstatustest.databinding.FragmentGcpBinding
import com.dbtechprojects.cloudstatustest.repository.MainRepository
import com.dbtechprojects.cloudstatustest.ui.adapters.GcpItemListAdapter
import com.dbtechprojects.cloudstatustest.ui.main.viewmodels.GcpFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class GcpFragment : Fragment(R.layout.fragment_gcp) {

    private lateinit var binding: FragmentGcpBinding
    private lateinit var mainFragment: MainFragment
    private val viewModel: GcpFragmentViewModel by viewModels()
    private val feedListAdapter by lazy {
        GcpItemListAdapter()
    }
    private var shouldNotifyAboutNewItems by Delegates.notNull<Boolean>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGcpBinding.bind(view)
        binding.gcpFeed.adapter = feedListAdapter
        mainFragment = requireParentFragment().requireParentFragment() as MainFragment
        shouldNotifyAboutNewItems = false

        binding.swipeToRefreshLayout.setOnRefreshListener {
            viewModel.fetchResults()
        }
        viewModel.apiFetchResult.observe(viewLifecycleOwner) {
            binding.swipeToRefreshLayout.isRefreshing = it == MainRepository.State.LOADING
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
            binding.gcpFeed.smoothScrollToPosition(0)
        }
    }
}
