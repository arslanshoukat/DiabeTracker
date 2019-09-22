package com.alharoof.diabetracker.ui.wizard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.extensions.intText
import com.alharoof.diabetracker.extensions.isTextNotZero
import kotlinx.android.synthetic.main.target_ranges_wizard_fragment.etHigh
import kotlinx.android.synthetic.main.target_ranges_wizard_fragment.etHyper
import kotlinx.android.synthetic.main.target_ranges_wizard_fragment.etHypo
import kotlinx.android.synthetic.main.target_ranges_wizard_fragment.etIcr
import kotlinx.android.synthetic.main.target_ranges_wizard_fragment.etIsf
import kotlinx.android.synthetic.main.target_ranges_wizard_fragment.etLow
import kotlinx.android.synthetic.main.target_ranges_wizard_fragment.etTarget
import kotlinx.android.synthetic.main.target_ranges_wizard_fragment.tilHigh
import kotlinx.android.synthetic.main.target_ranges_wizard_fragment.tilHyper
import kotlinx.android.synthetic.main.target_ranges_wizard_fragment.tilHypo
import kotlinx.android.synthetic.main.target_ranges_wizard_fragment.tilIcr
import kotlinx.android.synthetic.main.target_ranges_wizard_fragment.tilIsf
import kotlinx.android.synthetic.main.target_ranges_wizard_fragment.tilLow
import kotlinx.android.synthetic.main.target_ranges_wizard_fragment.tilTarget

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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TargetRangesWizardViewModel::class.java)
        setInitialValues()
    }

    private fun setInitialValues() {
        etLow.setText("${viewModel.getLowBgl()}")
        etHypo.setText("${viewModel.getHypoBgl()}")
        etTarget.setText("${viewModel.getTargetBgl()}")
        etHigh.setText("${viewModel.getHighBgl()}")
        etHyper.setText("${viewModel.getHyperBgl()}")
        etIsf.setText("${viewModel.getInsulinSensitivityFactor()}")
        etIcr.setText("${viewModel.getInsulinToCarbRatio()}")
    }

    override fun clearErrors() {
        tilTarget.error = ""
        tilLow.error = ""
        tilHypo.error = ""
        tilHigh.error = ""
        tilHyper.error = ""
        tilIsf.error = ""
        tilIcr.error = ""
    }

    override fun saveInputs(): Boolean {
        val isTargetValid = etTarget.isTextNotZero()
        val isLowValid = etLow.isTextNotZero()
        val isHypoValid = etHypo.isTextNotZero()
        val isHighValid = etHigh.isTextNotZero()
        val isHyperValid = etHyper.isTextNotZero()
        val areBglTargetAndRangesValid = isTargetValid && isLowValid && isHypoValid && isHighValid && isHyperValid

        //  set appropriate error or hide error if valid input is entered
        tilTarget.error = if (isTargetValid) "" else getString(R.string.error_required)
        tilLow.error = if (isLowValid) "" else getString(R.string.error_required)
        tilHypo.error = if (isHypoValid) "" else getString(R.string.error_value_non_zero)
        tilHigh.error = if (isHighValid) "" else getString(R.string.error_value_non_zero)
        tilHyper.error = if (isHyperValid) "" else getString(R.string.error_value_non_zero)

        val isf = etIsf.text.toString().toFloatOrNull() ?: 0.0f
        val icr = etIcr.text.toString().toFloatOrNull() ?: 0.0f
        val isIsfValid = isf != 0.0f
        val isIcrValid = icr != 0.0f

        tilIsf.error = if (isIsfValid) "" else getString(R.string.error_required)
        tilIcr.error = if (isIcrValid) "" else getString(R.string.error_required)

        //  check to ensure all fields are updated simultaneously when valid or not updated at all
        if (areBglTargetAndRangesValid && isIsfValid && isIcrValid) {
            viewModel.updateBglTargetAndRanges(
                target = etTarget.intText(),
                low = etLow.intText(),
                hypo = etHypo.intText(),
                high = etHigh.intText(),
                hyper = etHyper.intText()
            )
            viewModel.updateInsulinSensitivityFactor(isf)
            viewModel.updateInsulinToCarbRatio(icr)
        }

        return areBglTargetAndRangesValid && isIsfValid && isIcrValid
    }
}
