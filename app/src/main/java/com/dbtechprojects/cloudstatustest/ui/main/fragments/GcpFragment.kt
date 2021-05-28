package com.dbtechprojects.cloudstatustest.ui.main.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dbtechprojects.cloudstatustest.R
import com.dbtechprojects.cloudstatustest.databinding.FragmentGcpBinding
import com.dbtechprojects.cloudstatustest.ui.adapters.GcpItemListAdapter
import com.dbtechprojects.cloudstatustest.ui.main.MainActivity
import com.dbtechprojects.cloudstatustest.ui.main.viewmodels.GcpFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GcpFragment : Fragment(R.layout.fragment_gcp) {
    private lateinit var binding: FragmentGcpBinding
    val mainActivity: MainActivity by lazy {
        requireActivity() as MainActivity
    }
    private val viewModel: GcpFragmentViewModel by viewModels()
    private lateinit var gcpItemListAdapter: GcpItemListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGcpBinding.bind(view)
    }
}
