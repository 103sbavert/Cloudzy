package com.dbtechprojects.cloudstatustest.ui.main.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.dbtechprojects.cloudstatustest.R
import com.dbtechprojects.cloudstatustest.databinding.FragmentSettingsBinding
import com.dbtechprojects.cloudstatustest.ui.main.MainActivity

class SettingsFragment : Fragment(R.layout.fragment_settings), AdapterView.OnItemSelectedListener {

    lateinit var binding: FragmentSettingsBinding
    val mainActivity: MainActivity by lazy {
        requireActivity() as MainActivity
    }

    private val TAG = "SettingsFragment"
    lateinit var pref: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)

        //instantiate SharedPreferences
        pref = activity?.getPreferences(Context.MODE_PRIVATE)!!

        notificationStatusCheck()
        setupCheckListeners()
        setupProductNotificationSelector()

    }

    private fun setupProductNotificationSelector() {
        // declare list of options
        val options = listOf<String>("AWS Notifications Only", "GCP Notifications Only", "Azure Notifications Only", "All Notifications")

        // setup adapter
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item, options)
        binding.settingsProductNotificationsSelectorDropDown.adapter = adapter
        binding.settingsProductNotificationsSelectorDropDown.onItemSelectedListener = this

        //populate spinner with selected option or set default
        populateSpinner()

    }

    private fun populateSpinner() {
        pref.let {
            // check for a specific setting
            val status = it.getString("Specific Notification Selection", "")
            Log.d(TAG, "spinner status : ${status.toString()}")

            if (status.isNullOrEmpty()){
                // set to default
                binding.settingsProductNotificationsSelectorDropDown.setSelection(3)

            } else {
                // setting has already been set so lets set the drop down to the user's setting
                binding.settingsProductNotificationsSelectorDropDown.setSelection(status.toInt())
            }
        }
    }

    private fun setupCheckListeners() {
        binding.settingsNotificationsSwitch.setOnCheckedChangeListener { buttonView, isChecked ->

            pref.let {
                val prefEdit = it.edit()
                if (prefEdit != null) {
                    if (isChecked){
                        /* check box is checked so we will set preferences value to 1 for Disabled, this will then cancel all WorkManager tasks
                         when MainActivity is next created */
                        prefEdit.putString("Notification_Status", "1")
                        prefEdit.apply()
                    } else {
                        /* check box is unchecked so we will set preferences value to 0 for enabled and will cause WorkManager workers to be recreated
                         when MainActivity is next created */
                        prefEdit.putString("Notification_Status", "0")
                        prefEdit.apply()
                    }

                }

            }

        }
    }

    private fun notificationStatusCheck() {
        // check preferences and set Notifications Master switch accordingly

        pref.let { preferences ->

            val status = preferences.getString("Notification_Status", "")

            if (status.isNullOrEmpty()) {
                binding.settingsNotificationsSwitch.isChecked = false // show "disable notifications as unchecked.
            }

            if (status == "1") {
                // notifications are disabled
                binding.settingsNotificationsSwitch.isChecked = true

            }

            if (status == "0") {
                // notifications are enabled
                binding.settingsNotificationsSwitch.isChecked = false
            }

        }

    }



    // Specific notification settings will be set as follows in shared preferences
    /*
            preferences string will be "Specific Notification Selection"

            default will be  3 -> "All Notifications"

            0 -> "AWS Notifications Only"
            1 -> "GCP Notifications Only"
            2 -> "Azure Notifications Only"
            3 -> "All Notifications" (Default)
     */


    // override methods for product notification settings dropdown

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // log selection
        Log.d(TAG, "onItemSelected: postion of item is $position")

        // set notification setting in shared preferences
        val edit = pref.edit()
        edit.putString("Specific Notification Selection", position.toString())
        edit.apply()

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}