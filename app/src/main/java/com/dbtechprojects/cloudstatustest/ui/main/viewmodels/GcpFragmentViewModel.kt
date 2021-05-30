package com.dbtechprojects.cloudstatustest.ui.main.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dbtechprojects.cloudstatustest.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "GcpViewModel"

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class GcpFragmentViewModel
@Inject
constructor(private val repository: MainRepository) : ViewModel() {

    private val dao = repository.getCacheDatabaseDao()
    val gcpEvents = dao.getGcpEventsLiveData()

    init {
        fetchResults()
    }

    fun fetchResults() {
        viewModelScope.launch {
            repository.fetchFromGcpApi()
        }
    }
}
