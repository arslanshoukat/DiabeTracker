package com.alharoof.diabetracker.ui.wizard

import androidx.lifecycle.ViewModel
import com.alharoof.diabetracker.data.settings.PrefManager
import javax.inject.Inject

class TreatmentWizardViewModel @Inject constructor(
    private val prefManager: PrefManager
) : ViewModel() {

    fun updateBasalInsulin(basal: Int) {
        prefManager.setBasalInsulin(basal)
    }

    fun updateBolusInsulin(bolus: Int) {
        prefManager.setBolusInsulin(bolus)
    }
}
