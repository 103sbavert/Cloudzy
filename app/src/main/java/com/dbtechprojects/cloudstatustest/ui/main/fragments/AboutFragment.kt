package com.dbtechprojects.cloudstatustest.ui.main.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dbtechprojects.cloudstatustest.R
import com.dbtechprojects.cloudstatustest.databinding.FragmentAboutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutFragment : Fragment(R.layout.fragment_about) {

    lateinit var binding: FragmentAboutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAboutBinding.bind(view)
    }
}
