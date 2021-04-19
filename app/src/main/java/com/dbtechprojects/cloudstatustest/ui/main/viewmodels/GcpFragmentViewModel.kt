package com.dbtechprojects.cloudstatustest.ui.main.viewmodels

import androidx.lifecycle.ViewModel
import com.dbtechprojects.cloudstatustest.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "GcpViewModel"

@HiltViewModel
class GcpFragmentViewModel
@Inject
constructor(private val repository: Repository) : ViewModel() {

    
}