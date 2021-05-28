package com.dbtechprojects.cloudstatustest.ui.main.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dbtechprojects.cloudstatustest.model.AwsItem
import com.dbtechprojects.cloudstatustest.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class MainFragmentViewModel
@Inject
constructor(private val repository: MainRepository, @ApplicationContext private val context: Context) : ViewModel() {

    private val dao = repository.getCacheDatabaseDao()
    val awsEvents = MutableLiveData<List<AwsItem>>() //dao.getAwsEventsLiveData()
    private var isFirstTime = true

    init {
        fetchResults()
    }

    fun fetchResults() {
        viewModelScope.launch {
            val oldList = dao.getAwsEvents()
            repository.fetchFromAwsApi()
            val newList = dao.getAwsEvents()
            if (oldList == newList) {
                if (isFirstTime) {
                    awsEvents.postValue(newList)
                    isFirstTime = false
                }
                Toast.makeText(context, "No new results found", Toast.LENGTH_SHORT).show()
            } else if (oldList != newList) {
                awsEvents.postValue(newList)
                Toast.makeText(context, "New results found", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
