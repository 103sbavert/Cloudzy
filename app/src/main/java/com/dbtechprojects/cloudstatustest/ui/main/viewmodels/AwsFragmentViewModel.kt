package com.dbtechprojects.cloudstatustest.ui.main.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dbtechprojects.cloudstatustest.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainFragmentViewModel
@Inject
constructor(private val repository: MainRepository) : ViewModel() {

    private val dao = repository.getCacheDatabaseDao()
    val feed = dao.getAwsEventsLiveData()
    val apiFetchResult = repository.awsApiFetchResult

    init {
        fetchResults()
    }

    fun fetchResults() {
        viewModelScope.launch {
            repository.fetchFromAwsApi()
        }
    }
}
