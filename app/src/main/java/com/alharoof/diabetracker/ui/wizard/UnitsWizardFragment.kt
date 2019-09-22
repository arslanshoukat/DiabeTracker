package com.alharoof.diabetracker.ui.wizard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProviders
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.data.logbook.model.BglUnit
import com.alharoof.diabetracker.data.logbook.model.UnitOfMeasurement
import com.alharoof.diabetracker.util.Constants
import com.alharoof.diabetracker.util.Constants.INITIAL_SPINNER_POS
import com.alharoof.diabetracker.util.Converters
import kotlinx.android.synthetic.main.units_wizard_fragment.actvBglUnit
import kotlinx.android.synthetic.main.units_wizard_fragment.actvUnitOfMeasurement

class UnitsWizardFragment private constructor() : WizardFragment(TAG) {

    companion object {
        private const val TAG = "UnitsWizardFrag"
        private val unitsOfMeasurement = UnitOfMeasurement.values().toList()
        private val bglUnits = BglUnit.values().toList()

        fun newInstance() = UnitsWizardFragment()
    }

    private lateinit var viewModel: UnitsWizardViewModel
    private lateinit var ctx: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.units_wizard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UnitsWizardViewModel::class.java)
        initializeDropdowns()
    }

    private fun initializeDropdowns() {
        val initialUnitOfMeasurementCode = viewModel.getUnitOfMeasurement()
        val initialBglUnitCode = viewModel.getBglUnit()

        val unitOfMeasurementDropdownValues = unitsOfMeasurement.map { "${it.title} (${it.desc})" }
        val bglUnitDropdownValues = bglUnits.map { "${it.title} (${it.symbol})" }

        val unitsOfMeasurementAdapter =
            ArrayAdapter(ctx, R.layout.dropdown_menu_popup_item, unitOfMeasurementDropdownValues)
        actvUnitOfMeasurement.setAdapter(unitsOfMeasurementAdapter)
        //  setting initial dropdown value
        if (initialUnitOfMeasurementCode != Constants.INVALID_INT) {
            val unitOfMeasurement =
                Converters.fromUnitOfMeasurementCodeToEnum(initialUnitOfMeasurementCode)
                    ?.let { "${it.title} (${it.desc})" }
                    ?: unitOfMeasurementDropdownValues[INITIAL_SPINNER_POS] // if failed to convert, set to default
            actvUnitOfMeasurement.setText(unitOfMeasurement, false)
        } else {
            actvUnitOfMeasurement.setText(unitOfMeasurementDropdownValues[INITIAL_SPINNER_POS], false)
        }

        val bglUnitAdapter = ArrayAdapter(ctx, R.layout.dropdown_menu_popup_item, bglUnitDropdownValues)
        actvBglUnit.setAdapter(bglUnitAdapter)
        //  setting initial dropdown value
        if (initialBglUnitCode != Constants.INVALID_INT) {
            val bglUnit = Converters.fromBglUnitCodeToEnum(initialBglUnitCode)
                ?.let { "${it.title} (${it.symbol})" }
                ?: bglUnitDropdownValues[INITIAL_SPINNER_POS] // if failed to convert, set to default
            actvBglUnit.setText(bglUnit, false)
        } else {
            actvBglUnit.setText(bglUnitDropdownValues[INITIAL_SPINNER_POS], false)
        }
    }

    override fun clearErrors() {}

    override fun saveInputs(): Boolean {
        Converters.getUnitOfMeasurementFromTitle(actvUnitOfMeasurement.text.toString())?.code?.let {
            viewModel.updateUnitOfMeasurement(it)
        }
        Converters.getBglUnitFromTitle(actvBglUnit.text.toString())?.code?.let {
            viewModel.updateBglUnit(it)
        }
        return true
    }
}
