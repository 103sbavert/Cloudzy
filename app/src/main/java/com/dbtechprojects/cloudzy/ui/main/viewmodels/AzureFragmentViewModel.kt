package com.dbtechprojects.cloudzy.ui.main.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dbtechprojects.cloudzy.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AzureFragmentViewModel
@Inject
constructor(private val repository: MainRepository) : ViewModel() {


    init {
        updateDb()
        Log.d("azure", "init")
    }

    fun updateDb() {
        viewModelScope.launch {
            repository.updateAzureDb()
        }
    }
}
