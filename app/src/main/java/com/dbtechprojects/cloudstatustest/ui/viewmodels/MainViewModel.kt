@file:Suppress("DEPRECATION")

package com.dbtechprojects.cloudstatustest.ui.viewmodels

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dbtechprojects.cloudstatustest.model.AwsItem
import com.dbtechprojects.cloudstatustest.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


private const val TAG = "MainViewModel"
class MainViewModel @ViewModelInject constructor(
private val repository: Repository,
) : ViewModel() {

    val awsEvents: MutableLiveData<List<AwsItem>> = MutableLiveData()

    fun getAwsEvent(){
        viewModelScope.launch(Dispatchers.IO) {
            val test = async {
                repository.getAwsEvent()
            }
            if (test.await().toString() == "") {
                println("error")
            } else {

                val data = test.await()
                if (data != null) {

                    Log.d(TAG, data.message())
                    Log.d(TAG, data.body().toString())
                    Log.d(TAG, data.body()?.channel.toString())

                    data.body()?.channel?.item.let { items ->
                        if (items != null) {
                            for(i in items){
                                Log.d("item", i.toString())
                            }
                            awsEvents.postValue(items)
                        }
                    }
                }

                }
        }
    }

}