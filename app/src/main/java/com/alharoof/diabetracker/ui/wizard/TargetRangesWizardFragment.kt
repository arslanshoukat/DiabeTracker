package com.alharoof.diabetracker.ui.wizard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.util.Constants
import kotlinx.android.synthetic.main.target_ranges_wizard_fragment.etHigh
import kotlinx.android.synthetic.main.target_ranges_wizard_fragment.etHyper
import kotlinx.android.synthetic.main.target_ranges_wizard_fragment.etHypo
import kotlinx.android.synthetic.main.target_ranges_wizard_fragment.etIcr
import kotlinx.android.synthetic.main.target_ranges_wizard_fragment.etIsf
import kotlinx.android.synthetic.main.target_ranges_wizard_fragment.etLow
import kotlinx.android.synthetic.main.target_ranges_wizard_fragment.etTarget

class TargetRangesWizardFragment private constructor() : WizardFragment(TAG) {

    companion object {
        private const val TAG = "TargetRangesWizardFrag"

        fun newInstance() = TargetRangesWizardFragment()
    }

    private lateinit var viewModel: TargetRangesWizardViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.target_ranges_wizard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TargetRangesWizardViewModel::class.java)

        setInitialValues()
    }

    private fun setInitialValues() {
        etLow.setText("${Constants.DEFAULT_LOW_BGL}")
        etHypo.setText("${Constants.DEFAULT_HYPO_BGL}")
        etTarget.setText("${Constants.DEFAULT_TARGET_BGL}")
        etHigh.setText("${Constants.DEFAULT_HIGH_BGL}")
        etHyper.setText("${Constants.DEFAULT_HYPER_BGL}")

        etIcr.setText("${Constants.DEFAULT_INSULIN_TO_CARB_RATIO}")
        etIsf.setText("${Constants.DEFAULT_INSULIN_SENSITIVITY_FACTOR}")
    }

    override fun saveInputs() {
    }
}
