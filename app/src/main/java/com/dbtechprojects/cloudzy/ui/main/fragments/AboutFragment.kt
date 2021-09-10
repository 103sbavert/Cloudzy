package com.dbtechprojects.cloudzy.ui.main.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dbtechprojects.cloudzy.R
import com.dbtechprojects.cloudzy.databinding.FragmentAboutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutFragment : Fragment(R.layout.fragment_about) {

    lateinit var binding: FragmentAboutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAboutBinding.bind(view)
    }
}
