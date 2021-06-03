package com.dbtechprojects.cloudzy.ui.main.viewmodels

import androidx.lifecycle.ViewModel
import com.dbtechprojects.cloudzy.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AzureFragmentViewModel
@Inject
constructor(private val mainRepository: MainRepository) : ViewModel()
