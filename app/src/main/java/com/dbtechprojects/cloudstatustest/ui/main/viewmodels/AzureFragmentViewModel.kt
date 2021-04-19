package com.dbtechprojects.cloudstatustest.ui.main.viewmodels

import androidx.lifecycle.ViewModel
import com.dbtechprojects.cloudstatustest.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "AzureViewModel"

@HiltViewModel
class AzureFragmentViewModel
@Inject
constructor(private val repository: Repository) : ViewModel() {


}
