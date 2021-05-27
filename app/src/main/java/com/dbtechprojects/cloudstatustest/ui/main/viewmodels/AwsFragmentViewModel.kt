package com.dbtechprojects.cloudstatustest.ui.main.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dbtechprojects.cloudstatustest.model.AWSFeed
import com.dbtechprojects.cloudstatustest.model.AwsItem
import com.dbtechprojects.cloudstatustest.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

private const val TAG = "MainViewModel"

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class MainFragmentViewModel
@Inject
constructor(private val repository: Repository, @ApplicationContext  private val context: Context) : ViewModel() {

    private val dao = repository.getDatabaseDao()
    val awsEvents = dao.getAwsEvents()

    init {
        getAwsEvent()
    }

    fun getAwsEvent() {
            repository.getAwsCall().enqueue(
                object : Callback<AWSFeed> {
                    override fun onResponse(call: Call<AWSFeed>, response: Response<AWSFeed>) {
                        if (response.isSuccessful) {
                            response.body()?.channel?.itemList?.let { putInDb(it) }
                        }
                    }
                    override fun onFailure(call: Call<AWSFeed>, t: Throwable) {
                        Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()
                    }
                }
            )
    }

    private fun putInDb(itemList: List<AwsItem>) {
        viewModelScope.launch(Default) {
            for (i in itemList) {
                dao.insertAwsItem(i)
            }
        }
    }
}
