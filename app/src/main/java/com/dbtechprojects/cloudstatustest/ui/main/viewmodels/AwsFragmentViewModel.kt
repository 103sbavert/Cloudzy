package com.dbtechprojects.cloudstatustest.ui.main.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dbtechprojects.cloudstatustest.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val TAG = "MainViewModel"

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class MainFragmentViewModel
@Inject
constructor(private val mainRepository: MainRepository, @ApplicationContext  private val context: Context) : ViewModel() {

    private val dao = mainRepository.getDatabaseDao()
    val awsEvents = dao.getAwsEvents()

    init {
        fetchResults()
    }

    fun fetchResults() = mainRepository.fetchFromAwsApi(viewModelScope)
}
