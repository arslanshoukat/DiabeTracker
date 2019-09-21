package com.alharoof.diabetracker.ui.wizard

import androidx.lifecycle.ViewModel
import com.alharoof.diabetracker.data.settings.PrefManager
import javax.inject.Inject

class PersonalInfoWizardViewModel @Inject constructor(
    private val prefManager: PrefManager
) : ViewModel() {

    fun updateName(firstName: String, lastName: String) {
        prefManager.setFirstName(firstName)
        prefManager.setLastName(lastName)
    }

    fun updateHeight(height: Float) {
        prefManager.setHeight(height)
    }

    fun updateWeight(weight: Float) {
        prefManager.setWeight(weight)
    }

    fun getFirstName(): String? {
        return prefManager.getFirstName()
    }

    fun getLastName(): String? {
        return prefManager.getLastName()
    }

    fun getWeight(): Float {
        return prefManager.getWeight()
    }

    fun getHeight(): Float {
        return prefManager.getHeight()
    }
}
