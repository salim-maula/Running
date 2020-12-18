package com.example.running.repositories.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.running.R
import com.example.running.other.Constants.KEY_FIRST_TIME_TOGGLE
import com.example.running.other.Constants.KEY_NAME
import com.example.running.other.Constants.KEY_WEIGHT
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_setup.*
import javax.inject.Inject

@AndroidEntryPoint
class SetupFragment : Fragment(R.layout.fragment_setup) {

    @Inject
    lateinit var sharedPref: SharedPreferences

    @set:Inject
    var isFirstAppOpen = true

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        if (!isFirstAppOpen){
            val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.setupFragment2, true)
                    .build()
            findNavController().navigate(
                    R.id.action_setupFragment2_to_runFragment2,
                    savedInstanceState,
                    navOptions
            )
        }

        tvContinue.setOnClickListener{
            val success = writePersonalDataToSharedPref()
            if (success){
                findNavController().navigate(R.id.action_setupFragment2_to_runFragment2)
            }   else{
                Snackbar.make(requireView(), "Please enter all the fields", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun writePersonalDataToSharedPref(): Boolean{
        val name = etName.text.toString()
        val weight = etWeight.text.toString()
        if (name.isEmpty() || weight.isEmpty()){
            return false
        }
        sharedPref.edit()
                .putString(KEY_NAME, name)
                .putFloat(KEY_WEIGHT, weight.toFloat())
                .putBoolean(KEY_FIRST_TIME_TOGGLE, false)
                .apply()
        val toolbarText = "Let's go, $name!"
        requireActivity().tvToolbarTitle.text = toolbarText
        return true
    }
}