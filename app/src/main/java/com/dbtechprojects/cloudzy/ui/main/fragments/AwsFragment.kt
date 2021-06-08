package com.dbtechprojects.cloudzy.ui.main.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dbtechprojects.cloudzy.R
import com.dbtechprojects.cloudzy.databinding.FragmentAwsBinding
import com.dbtechprojects.cloudzy.repository.MainRepository
import com.dbtechprojects.cloudzy.ui.adapters.AwsItemListAdapter
import com.dbtechprojects.cloudzy.ui.main.viewmodels.AwsFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AwsFragment : Fragment(R.layout.fragment_aws) {

    private lateinit var binding: FragmentAwsBinding
    private lateinit var mainFragment: MainFragment
    private val viewModel: AwsFragmentViewModel by viewModels()
    private val feedListAdapter by lazy {
        AwsItemListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAwsBinding.bind(view)
        mainFragment = requireParentFragment().requireParentFragment() as MainFragment

        binding.awsFeed.adapter = feedListAdapter
        binding.swipeToRefreshLayout.setOnRefreshListener {
            viewModel.updateDb()
        }

        lifecycleScope.launch {
            feedListAdapter.submitList(viewModel.getItemsFromDb())
        }

        viewModel.apiFetchResult.observe(viewLifecycleOwner) {
            binding.swipeToRefreshLayout.isRefreshing = it == MainRepository.State.LOADING
        }
        viewModel.wasDbUpdated.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "New Items Found", Toast.LENGTH_SHORT).show()
                lifecycleScope.launch {
                    feedListAdapter.submitList(viewModel.getItemsFromDb())
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainFragment.binding.bottomNavigationBar.setOnNavigationItemReselectedListener {
            viewModel.updateDb()
            binding.awsFeed.smoothScrollToPosition(0)
        }
    }
}
