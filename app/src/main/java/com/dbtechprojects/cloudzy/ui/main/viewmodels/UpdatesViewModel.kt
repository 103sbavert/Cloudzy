package com.dbtechprojects.cloudzy.ui.main.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dbtechprojects.cloudzy.model.Update
import com.dbtechprojects.cloudzy.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdatesViewModel
@Inject
constructor(private val repository: MainRepository) : ViewModel() {

    private val dao = repository.getCacheDatabaseDao()
    private val _updatesLiveData = MutableLiveData<List<Update>>()
    val updatesLiveData
        get() = _updatesLiveData

    fun getUpdatesById(id: String) {
        viewModelScope.launch {
            val item = dao.getGcpEventUpdatesById(id)
            item.updates?.let { _updatesLiveData.postValue(it) }
        }
    }
}
