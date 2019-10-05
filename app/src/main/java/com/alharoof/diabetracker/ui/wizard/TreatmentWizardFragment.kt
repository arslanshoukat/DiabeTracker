package com.alharoof.diabetracker.ui.wizard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProviders
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.util.Constants
import com.alharoof.diabetracker.util.Converters
import com.alharoof.diabetracker.util.getBasalInsulins
import com.alharoof.diabetracker.util.getBolusInsulins
import kotlinx.android.synthetic.main.treatment_wizard_fragment.actvBasal
import kotlinx.android.synthetic.main.treatment_wizard_fragment.actvBolus

class TreatmentWizardFragment private constructor() : BaseWizardFragment(TAG) {

    companion object {
        private const val TAG = "TreatmentWizardFrag"
        private val basalInsulins = getBasalInsulins()
        private val bolusInsulins = getBolusInsulins()

        fun newInstance() = TreatmentWizardFragment()
    }

    private lateinit var viewModel: TreatmentWizardViewModel
    private lateinit var ctx: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.treatment_wizard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TreatmentWizardViewModel::class.java)
        initializeDropdowns()
    }

    private fun initializeDropdowns() {
        val initialBasal = viewModel.getBasalInsulin()
        val initialBolus = viewModel.getBolusInsulin()
        val basalList = basalInsulins.map { it.productName }
        val bolusList = bolusInsulins.map { it.productName }

        val basalAdapter = ArrayAdapter(ctx, R.layout.dropdown_menu_popup_item, basalList)
        actvBasal.setAdapter(basalAdapter)
        if (initialBasal != Constants.INVALID_INT) {
            //  set dropdown to user selected value if user saved basal before
            actvBasal.setText(
                Converters.fromMedicationCodeToEnum(initialBasal)?.productName ?: basalList[0],
                false
            )
        } else {
            //  set dropdown to default value if user never selected basal before
            actvBasal.setText(basalList[0], false)
        }

        val bolusAdapter = ArrayAdapter(ctx, R.layout.dropdown_menu_popup_item, bolusList)
        actvBolus.setAdapter(bolusAdapter)
        if (initialBolus != Constants.INVALID_INT) {
            //  set dropdown to user selected value if user saved bolus before
            actvBolus.setText(
                Converters.fromMedicationCodeToEnum(initialBolus)?.productName ?: bolusList[0],
                false
            )
        } else {
            //  set dropdown to default value if user never selected bolus before
            actvBolus.setText(bolusList[0], false)
        }
    }

    override fun clearErrors() {}

    override fun saveInputs(): Boolean {
        Converters.getMedicationFromProductName(actvBasal.text.toString())?.code?.let {
            viewModel.updateBasalInsulin(it)
        }
        Converters.getMedicationFromProductName(actvBolus.text.toString())?.code?.let {
            viewModel.updateBolusInsulin(it)
        }
        return true
    }
}
