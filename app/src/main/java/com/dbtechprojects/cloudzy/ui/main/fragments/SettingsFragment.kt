package com.dbtechprojects.cloudzy.ui.main.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.dbtechprojects.cloudzy.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_screen, rootKey)
    }
}
