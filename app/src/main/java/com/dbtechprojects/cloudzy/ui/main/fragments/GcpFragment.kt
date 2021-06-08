package com.dbtechprojects.cloudzy.ui.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dbtechprojects.cloudzy.R
import com.dbtechprojects.cloudzy.databinding.FragmentGcpBinding
import com.dbtechprojects.cloudzy.repository.MainRepository
import com.dbtechprojects.cloudzy.ui.adapters.GcpItemListAdapter
import com.dbtechprojects.cloudzy.ui.main.viewmodels.GcpFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GcpFragment : Fragment(R.layout.fragment_gcp), GcpItemListAdapter.OnButtonsClickListener {

    private lateinit var binding: FragmentGcpBinding
    private lateinit var mainFragment: MainFragment
    private val viewModel: GcpFragmentViewModel by viewModels()
    private val feedListAdapter by lazy {
        GcpItemListAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGcpBinding.bind(view)
        mainFragment = requireParentFragment().requireParentFragment() as MainFragment

        binding.gcpFeed.adapter = feedListAdapter
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
            binding.gcpFeed.smoothScrollToPosition(0)
        }
    }

    override fun onUpdatesButtonClickListener(id: String) {
        findNavController().navigate(GcpFragmentDirections.actionGcpFragmentToUpdatesDialogFragment(id))
    }
}
