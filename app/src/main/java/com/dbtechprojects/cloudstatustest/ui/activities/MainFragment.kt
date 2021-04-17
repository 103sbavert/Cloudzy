package com.dbtechprojects.cloudstatustest.ui.activities

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dbtechprojects.cloudstatustest.R
import com.dbtechprojects.cloudstatustest.databinding.FragmentMainBinding
import com.dbtechprojects.cloudstatustest.ui.adapters.AwsItemListAdapter
import com.dbtechprojects.cloudstatustest.ui.viewmodels.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    lateinit var binding: FragmentMainBinding
    val mainActivity: MainActivity by lazy {
        requireActivity() as MainActivity
    }
    private val viewModel: MainFragmentViewModel by viewModels()
    private lateinit var awsItemListAdapter: AwsItemListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        viewModel.getAwsEvent()

        viewModel.awsEvents.observe(viewLifecycleOwner) {
            awsItemListAdapter = AwsItemListAdapter(it)
            binding.awsFeed.adapter = awsItemListAdapter
        }
    }
}
