package com.dbtechprojects.cloudstatustest.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.dbtechprojects.cloudstatustest.databinding.DialogFragmentUpdatesBinding
import com.dbtechprojects.cloudstatustest.ui.adapters.UpdatesListAdapter
import com.dbtechprojects.cloudstatustest.ui.main.viewmodels.UpdatesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdatesDialogFragment : BottomSheetDialogFragment() {

    private val viewModel: UpdatesViewModel by viewModels()
    private val navArgs: UpdatesDialogFragmentArgs by navArgs()
    private lateinit var binding: DialogFragmentUpdatesBinding
    private lateinit var adapter: UpdatesListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DialogFragmentUpdatesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUpdatesById(navArgs.id)
        binding.toolbar.setNavigationOnClickListener {
            dismiss()
        }
        viewModel.updatesLiveData.observe(viewLifecycleOwner) { list ->
            adapter = UpdatesListAdapter(list)
            binding.updatesRecyclerView.adapter = adapter
        }
    }
}
