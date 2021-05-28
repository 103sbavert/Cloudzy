package com.dbtechprojects.cloudstatustest.ui.main.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dbtechprojects.cloudstatustest.model.GcpItem
import com.dbtechprojects.cloudstatustest.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "GcpViewModel"

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class GcpFragmentViewModel
@Inject
constructor(private val repository: MainRepository, @ApplicationContext private val context: Context) : ViewModel() {

    private val dao = repository.getCacheDatabaseDao()
    val gcpEvents = MutableLiveData<List<GcpItem>>() //dao.getGcpEventsLiveData()
    private var isFirstTime = true

    init {
        fetchResults()
    }

    fun fetchResults() {
        viewModelScope.launch {
            val oldList = dao.getGcpEvents()
            repository.fetchFromGcpApi()
            val newList = dao.getGcpEvents()
            if (oldList == newList) {
                if (isFirstTime) {
                    gcpEvents.postValue(newList)
                    isFirstTime = false
                }
                Toast.makeText(context, "No new results found", Toast.LENGTH_SHORT).show()
            } else if (oldList != newList) {
                gcpEvents.postValue(newList)
                Toast.makeText(context, "New results found", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
