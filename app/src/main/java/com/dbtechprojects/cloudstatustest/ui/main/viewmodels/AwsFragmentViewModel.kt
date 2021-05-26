package com.dbtechprojects.cloudstatustest.ui.main.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dbtechprojects.cloudstatustest.model.AwsItem
import com.dbtechprojects.cloudstatustest.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainFragmentViewModel
@Inject
constructor(private val repository: Repository) : ViewModel() {

    private val database = repository.getDatabase()
    private val dao = database.cloudStatusDao()
    val awsEvents = dao.getAwsEvents()

    init {
        getAwsEvent()
    }

    fun getAwsEvent() {
        viewModelScope.launch(IO) {
            val itemList = repository.getAwsEvent().body()?.channel?.itemList
            if (itemList != null) putInDb(itemList)
        }
    }

    private suspend fun putInDb(itemList: List<AwsItem>) {
        withContext(Default) {
            for (i in itemList) {
                dao.insertAwsItem(i)
            }
        }
    }

}
