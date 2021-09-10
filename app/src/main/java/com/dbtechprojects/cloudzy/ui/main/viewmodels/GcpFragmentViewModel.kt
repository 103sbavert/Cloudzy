package com.dbtechprojects.cloudzy.ui.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dbtechprojects.cloudzy.model.GcpItem
import com.dbtechprojects.cloudzy.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GcpFragmentViewModel
@Inject
constructor(private val repository: MainRepository) : ViewModel() {

    private val dao = repository.getCacheDatabaseDao()
    private val _feed = MutableLiveData<List<GcpItem>>()
    val feed = dao.getGcpEventsLiveData()
    val apiFetchResult = repository.gcpApiFetchResult

    private val _wasDbUpdated = MutableLiveData(false)
    val wasDbUpdated: LiveData<Boolean>
        get() = _wasDbUpdated

    init {
        updateDb()
    }

    fun updateDb() = viewModelScope.launch {
        _wasDbUpdated.postValue(repository.updateGcpDb())
    }
}
