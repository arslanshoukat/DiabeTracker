package com.alharoof.diabetracker.ui.wizard

import androidx.lifecycle.ViewModel
import com.alharoof.diabetracker.data.settings.PrefManager
import javax.inject.Inject

class UnitsWizardViewModel @Inject constructor(
    private val prefManager: PrefManager
) : ViewModel() {

    fun updateBglUnit(bglUnit: Int) {
        prefManager.setBglUnit(bglUnit)
    }

    fun updateUnitOfMeasurement(unitOfMeasurement: Int) {
        prefManager.setUnitOfMeasurement(unitOfMeasurement)
    }
}
