package com.dbtechprojects.cloudstatustest.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dbtechprojects.cloudstatustest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainActivityBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainActivityBinding.root)
    }
}
