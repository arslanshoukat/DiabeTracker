package com.alharoof.diabetracker.ui.wizard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.util.Constants
import kotlinx.android.synthetic.main.personal_info_wizard_fragment.etFirstName
import kotlinx.android.synthetic.main.personal_info_wizard_fragment.etHeight
import kotlinx.android.synthetic.main.personal_info_wizard_fragment.etLastName
import kotlinx.android.synthetic.main.personal_info_wizard_fragment.etWeight
import kotlinx.android.synthetic.main.personal_info_wizard_fragment.tilFirstName
import kotlinx.android.synthetic.main.personal_info_wizard_fragment.tilHeight
import kotlinx.android.synthetic.main.personal_info_wizard_fragment.tilLastName
import kotlinx.android.synthetic.main.personal_info_wizard_fragment.tilWeight

class PersonalInfoWizardFragment private constructor() : WizardFragment(TAG) {

    companion object {
        private const val TAG = "PersonalInfoWizardFrag"

        fun newInstance() = PersonalInfoWizardFragment()
    }

    private lateinit var viewModel: PersonalInfoWizardViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.personal_info_wizard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PersonalInfoWizardViewModel::class.java)
        setInitialValues()
    }

    private fun setInitialValues() {
        etFirstName.setText(viewModel.getFirstName() ?: "")
        etLastName.setText(viewModel.getLastName() ?: "")
        if (viewModel.getWeight() != Constants.INVALID_FLOAT) {
            etWeight.setText("${viewModel.getWeight()}")
        }
        if (viewModel.getHeight() != Constants.INVALID_FLOAT) {
            etHeight.setText("${viewModel.getHeight()}")
        }
    }

    /**
     * This method performs validation checks on all input fields of this fragment and returns true if any single one of
     * them is invalid. It also shows appropriate error message when input is invalid.
     */
    private fun isAnyInputInvalid(firstName: String, lastName: String, height: Float, weight: Float): Boolean {
        val isFirstNameBlank = firstName.isBlank()
        val isLastNameBlank = lastName.isBlank()
        val isWeightZero = weight == 0.0f
        val isHeightZero = height == 0.0f

        //  set appropriate error or hide error if valid input is entered
        tilFirstName.error = if (isFirstNameBlank) getString(R.string.error_required) else ""
        tilLastName.error = if (isLastNameBlank) getString(R.string.error_required) else ""
        tilHeight.error = if (isHeightZero) getString(R.string.error_required) else ""
        tilWeight.error = if (isWeightZero) getString(R.string.error_required) else ""

        //  if any of above checks is true, that means at least one input is invalid
        return isFirstNameBlank || isLastNameBlank || isHeightZero || isWeightZero
    }

    override fun clearErrors() {
        tilFirstName.error = ""
        tilLastName.error = ""
        tilHeight.error = ""
        tilWeight.error = ""
    }

    override fun saveInputs(): Boolean {
        val firstName = etFirstName.text.toString()
        val lastName = etLastName.text.toString()
        val height = etHeight.text.toString().toFloatOrNull() ?: 0.0f
        val weight = etWeight.text.toString().toFloatOrNull() ?: 0.0f
        var inputsSaved = false

        if (!isAnyInputInvalid(firstName, lastName, height, weight)) {
            viewModel.updateName(firstName, lastName)
            viewModel.updateHeight(height)
            viewModel.updateWeight(weight)
            inputsSaved = true
        }

        return inputsSaved
    }
}
