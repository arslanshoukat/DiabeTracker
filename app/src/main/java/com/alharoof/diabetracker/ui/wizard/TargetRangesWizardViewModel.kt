package com.alharoof.diabetracker.ui.wizard

import androidx.lifecycle.ViewModel
import com.alharoof.diabetracker.data.settings.PrefManager
import javax.inject.Inject

class TargetRangesWizardViewModel @Inject constructor(
    private val prefManager: PrefManager
) : ViewModel() {

    fun updateBglTargetAndRanges(target: Int, low: Int, hypo: Int, high: Int, hyper: Int) {
        prefManager.setTargetBgl(target)
        prefManager.setLowBgl(low)
        prefManager.setHypoBgl(hypo)
        prefManager.setHighBgl(high)
        prefManager.setHyperBgl(hyper)
    }

    fun updateInsulinSensitivityFactor(isf: Float) {
        prefManager.setInsulinSensitivityFactor(isf)
    }

    fun updateInsulinToCarbRatio(icr: Float) {
        prefManager.setInsulinToCarbRatio(icr)
    }

    fun getTargetBgl(): Int {
        return prefManager.getTargetBgl()
    }

    fun getLowBgl(): Int {
        return prefManager.getLowBgl()
    }

    fun getHypoBgl(): Int {
        return prefManager.getHypoBgl()
    }

    fun getHighBgl(): Int {
        return prefManager.getHighBgl()
    }

    fun getHyperBgl(): Int {
        return prefManager.getHyperBgl()
    }

    fun getInsulinSensitivityFactor(): Float {
        return prefManager.getInsulinSensitivityFactor()
    }

    fun getInsulinToCarbRatio(): Float {
        return prefManager.getInsulinToCarbRatio()
    }
}
