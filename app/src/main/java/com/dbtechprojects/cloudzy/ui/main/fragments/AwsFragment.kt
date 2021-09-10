package com.dbtechprojects.cloudzy.ui.main.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dbtechprojects.cloudzy.R
import com.dbtechprojects.cloudzy.databinding.FragmentAwsBinding
import com.dbtechprojects.cloudzy.repository.MainRepository
import com.dbtechprojects.cloudzy.ui.adapters.AwsItemListAdapter
import com.dbtechprojects.cloudzy.ui.main.viewmodels.AwsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AwsFragment : Fragment(R.layout.fragment_aws), ScrollableFragment {

    lateinit var binding: FragmentAwsBinding
    val viewModel: AwsFragmentViewModel by viewModels()
    private val feedListAdapter by lazy {
        AwsItemListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAwsBinding.bind(view)
        binding.awsFeed.adapter = feedListAdapter

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
        binding.awsFeed.smoothScrollToPosition(0)
        binding.swipeToRefreshLayout.isRefreshing = true
        viewModel.updateDb()
    }
}
