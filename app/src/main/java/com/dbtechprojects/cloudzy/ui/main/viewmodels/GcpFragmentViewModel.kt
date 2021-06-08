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
    private val _wasDbUpdated = MutableLiveData<Boolean>()
    val wasDbUpdated: LiveData<Boolean>
        get() = _wasDbUpdated
    val apiFetchResult = repository.gcpApiFetchResult

    init {
        updateDb()
    }

    suspend fun getItemsFromDb(): List<GcpItem> {
        return dao.getGcpEvents()
    }

    fun updateDb() {
        viewModelScope.launch {
            _wasDbUpdated.postValue(repository.updateGcpDb())
        }
    }
}
