package com.dbtechprojects.cloudstatustest.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dbtechprojects.cloudstatustest.R
import com.dbtechprojects.cloudstatustest.model.AwsItem
import com.dbtechprojects.cloudstatustest.ui.AwsItemListAdapter
import com.dbtechprojects.cloudstatustest.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setup recyclerview
        val emptylist = listOf<AwsItem>()
        val rv = findViewById<RecyclerView>(R.id.recyclerview)



        // launch request
        viewModel.getAwsEvent()

        //observe results

        viewModel.awsEvents.observe(this, Observer { item ->
            rv.apply {
                layoutManager =LinearLayoutManager(this@MainActivity)
                adapter = AwsItemListAdapter(item)
            }

        })
    }
}