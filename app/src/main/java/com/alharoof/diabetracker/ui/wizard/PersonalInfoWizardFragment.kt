package com.alharoof.diabetracker.ui.wizard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.alharoof.diabetracker.R.layout

class PersonalInfoWizardFragment private constructor() : WizardFragment(TAG) {

    companion object {
        private const val TAG = "PersonalInfoWizardFrag"

        fun newInstance() = PersonalInfoWizardFragment()
    }

    private lateinit var viewModel: PersonalInfoWizardViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout.personal_info_wizard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PersonalInfoWizardViewModel::class.java)
    }

    override fun saveInputs() {
    }
}
