package com.dbtechprojects.cloudstatustest.ui.main.fragments
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dbtechprojects.cloudstatustest.R
import com.dbtechprojects.cloudstatustest.databinding.FragmentAzureBinding
import com.dbtechprojects.cloudstatustest.ui.adapters.AzureItemListAdapter
import com.dbtechprojects.cloudstatustest.ui.main.MainActivity
import com.dbtechprojects.cloudstatustest.ui.main.viewmodels.AzureFragmentViewModel
import com.dbtechprojects.cloudstatustest.ui.main.viewmodels.MainFragmentViewModel
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
    }
}