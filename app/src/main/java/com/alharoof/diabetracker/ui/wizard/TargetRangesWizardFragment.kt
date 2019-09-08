package com.alharoof.diabetracker.ui.wizard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.alharoof.diabetracker.R

class TargetRangesWizardFragment : Fragment() {

    companion object {
        fun newInstance() = TargetRangesWizardFragment()
    }

    private lateinit var viewModel: TargetRangesWizardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.target_ranges_wizard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TargetRangesWizardViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
