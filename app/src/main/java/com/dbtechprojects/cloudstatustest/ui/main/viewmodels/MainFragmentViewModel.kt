package com.dbtechprojects.cloudstatustest.ui.main.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dbtechprojects.cloudstatustest.model.AwsItem
import com.dbtechprojects.cloudstatustest.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"

@HiltViewModel
class MainFragmentViewModel
@Inject
constructor(private val repository: Repository) : ViewModel() {

    val awsEvents: MutableLiveData<List<AwsItem>> = MutableLiveData()

    fun getAwsEvent() {
        viewModelScope.launch(Dispatchers.Default) {
            awsEvents.postValue(repository.getAwsEvent().body()?.channel?.itemList)
        }
    }
}
