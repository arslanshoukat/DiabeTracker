package com.alharoof.diabetracker.ui.wizard

import android.R.layout
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
import kotlinx.android.synthetic.main.units_wizard_fragment.spBglUnit
import kotlinx.android.synthetic.main.units_wizard_fragment.spUnitsOfMeasurement

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
        val unitsOfMeasurementAdapter = ArrayAdapter(ctx, layout.simple_spinner_item,
            unitsOfMeasurement.map { "${it.title} (${it.desc})" })
        unitsOfMeasurementAdapter.setDropDownViewResource(layout.simple_spinner_dropdown_item)
        spUnitsOfMeasurement.prompt = "Units of Measurement"
        spUnitsOfMeasurement.adapter = unitsOfMeasurementAdapter

        val bglUnitAdapter = ArrayAdapter(ctx, layout.simple_spinner_item,
            bglUnits.map { "${it.title} (${it.symbol})" })
        bglUnitAdapter.setDropDownViewResource(layout.simple_spinner_dropdown_item)
        spBglUnit.prompt = "Unit of Blood Glucose Level"
        spBglUnit.adapter = bglUnitAdapter
    }

    override fun clearErrors() {}

    override fun saveInputs(): Boolean {
        viewModel.updateBglUnit(bglUnits[spBglUnit.selectedItemPosition].code)
        viewModel.updateUnitOfMeasurement(unitsOfMeasurement[spUnitsOfMeasurement.selectedItemPosition].code)
        return true
    }
}
