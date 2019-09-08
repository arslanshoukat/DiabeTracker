package com.alharoof.diabetracker.ui.wizard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.alharoof.diabetracker.R

class UnitsWizardFragment : Fragment() {

    companion object {
        fun newInstance() = UnitsWizardFragment()
    }

    private lateinit var viewModel: UnitsWizardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.units_wizard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UnitsWizardViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
