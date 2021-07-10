package com.dbtechprojects.cloudzy.ui.main.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dbtechprojects.cloudzy.R
import com.dbtechprojects.cloudzy.databinding.FragmentAzureBinding
import com.dbtechprojects.cloudzy.ui.adapters.AzureItemListAdapter
import com.dbtechprojects.cloudzy.ui.main.MainActivity
import com.dbtechprojects.cloudzy.ui.main.viewmodels.AzureFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AzureFragment : Fragment(R.layout.fragment_azure) {
    lateinit var binding: FragmentAzureBinding
    val mainActivity: MainActivity by lazy {
        requireActivity() as MainActivity
    }
    private val viewModel: AzureFragmentViewModel by viewModels()
    private lateinit var azureItemListAdapter: AzureItemListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAzureBinding.bind(view)

        viewModel.updateDb()
    }
}
