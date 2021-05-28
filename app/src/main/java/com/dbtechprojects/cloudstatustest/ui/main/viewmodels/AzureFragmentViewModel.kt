package com.dbtechprojects.cloudstatustest.ui.main.viewmodels

import androidx.lifecycle.ViewModel
import com.dbtechprojects.cloudstatustest.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "AzureViewModel"

@HiltViewModel
class AzureFragmentViewModel
@Inject
constructor(private val mainRepository: MainRepository) : ViewModel()
