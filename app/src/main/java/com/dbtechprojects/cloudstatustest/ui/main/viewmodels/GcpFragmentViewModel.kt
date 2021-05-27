package com.dbtechprojects.cloudstatustest.ui.main.viewmodels

import androidx.lifecycle.ViewModel
import com.dbtechprojects.cloudstatustest.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "GcpViewModel"

@HiltViewModel
class GcpFragmentViewModel
@Inject
constructor(private val mainRepository: MainRepository) : ViewModel() {


}
