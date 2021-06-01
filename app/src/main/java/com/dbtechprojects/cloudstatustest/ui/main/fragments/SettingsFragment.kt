package com.dbtechprojects.cloudstatustest.ui.main.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.dbtechprojects.cloudstatustest.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_screen, rootKey)
    }
}
