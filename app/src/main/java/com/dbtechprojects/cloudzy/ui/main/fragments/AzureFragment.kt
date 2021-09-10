package com.dbtechprojects.cloudzy.ui.main.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dbtechprojects.cloudzy.R
import com.dbtechprojects.cloudzy.databinding.FragmentAzureBinding
import com.dbtechprojects.cloudzy.repository.MainRepository
import com.dbtechprojects.cloudzy.ui.adapters.AzureItemListAdapter
import com.dbtechprojects.cloudzy.ui.main.viewmodels.AzureFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AzureFragment : Fragment(R.layout.fragment_azure), ScrollableFragment {

    lateinit var binding: FragmentAzureBinding
    private val viewModel: AzureFragmentViewModel by viewModels()
    private val feedListAdapter by lazy {
        AzureItemListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAzureBinding.bind(view)
        binding.azureFeed.adapter = feedListAdapter

        binding.swipeToRefreshLayout.setOnRefreshListener {
            viewModel.updateDb()
        }

        viewModel.apiFetchResult.observe(viewLifecycleOwner) {
            binding.swipeToRefreshLayout.isRefreshing = it is MainRepository.State.LOADING
        }

        viewModel.wasDbUpdated.observe(viewLifecycleOwner) {
            if (it) Toast.makeText(requireActivity(), "New Items Found", Toast.LENGTH_SHORT).show()
        }

        viewModel.feed.observe(viewLifecycleOwner) {
            feedListAdapter.submitList(it)
        }
    }

    override fun scrollToTop() {
        binding.azureFeed.smoothScrollToPosition(0)
        binding.swipeToRefreshLayout.isRefreshing = true
        viewModel.updateDb()
    }
}
