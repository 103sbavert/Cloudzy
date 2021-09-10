package com.dbtechprojects.cloudzy.ui.main.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dbtechprojects.cloudzy.R
import com.dbtechprojects.cloudzy.databinding.FragmentGcpBinding
import com.dbtechprojects.cloudzy.repository.MainRepository
import com.dbtechprojects.cloudzy.ui.adapters.GcpItemListAdapter
import com.dbtechprojects.cloudzy.ui.main.viewmodels.GcpFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GcpFragment : Fragment(R.layout.fragment_gcp), GcpItemListAdapter.OnButtonsClickListener, ScrollableFragment {

    lateinit var binding: FragmentGcpBinding
    val viewModel: GcpFragmentViewModel by viewModels()
    private val feedListAdapter by lazy {
        GcpItemListAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGcpBinding.bind(view)
        binding.gcpFeed.adapter = feedListAdapter

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

    override fun onSeeAllButtonClickListener(id: String) {
        findNavController().navigate(GcpFragmentDirections.actionGcpFragmentToUpdatesDialogFragment(id))
    }

    override fun scrollToTop() {
        binding.gcpFeed.smoothScrollToPosition(0)
        binding.swipeToRefreshLayout.isRefreshing = true
        viewModel.updateDb()
    }
}
