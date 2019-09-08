package com.alharoof.diabetracker.ui.wizard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.alharoof.diabetracker.R.layout

class PersonalInfoWizardFragment : Fragment() {

    companion object {
        fun newInstance() = PersonalInfoWizardFragment()
    }

    private lateinit var viewModel: PersonalInfoWizardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout.personal_info_wizard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PersonalInfoWizardViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
