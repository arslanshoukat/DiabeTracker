package com.alharoof.diabetracker.ui.wizard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.util.getBasalInsulins
import com.alharoof.diabetracker.util.getBolusInsulins
import kotlinx.android.synthetic.main.treatment_wizard_fragment.spBasalInsulins
import kotlinx.android.synthetic.main.treatment_wizard_fragment.spBolusInsulins

class TreatmentWizardFragment private constructor() : Fragment() {

    companion object {
        fun newInstance() = TreatmentWizardFragment()
    }

    private lateinit var viewModel: TreatmentWizardViewModel
    private lateinit var ctx: Context
    private val basalInsulins = getBasalInsulins()
    private val bolusInsulins = getBolusInsulins()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.treatment_wizard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TreatmentWizardViewModel::class.java)

        val basalAdapter =
            ArrayAdapter(ctx, android.R.layout.simple_spinner_item, basalInsulins.map { it.productName })
        basalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spBasalInsulins.prompt = "Intermediate/Long Acting Insulin"
        spBasalInsulins.adapter = basalAdapter

        val bolusAdapter =
            ArrayAdapter(ctx, android.R.layout.simple_spinner_item, bolusInsulins.map { it.productName })
        bolusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spBolusInsulins.prompt = "Rapid/Short Acting Insulin"
        spBolusInsulins.adapter = bolusAdapter
    }
}
