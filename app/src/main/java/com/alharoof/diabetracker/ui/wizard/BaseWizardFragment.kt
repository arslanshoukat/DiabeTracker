package com.alharoof.diabetracker.ui.wizard

import androidx.lifecycle.ViewModelProvider
import com.alharoof.diabetracker.base.BaseFragment
import javax.inject.Inject

/**
 * Created by Arslan Shoukat on Sep 18, 2019 9:29 AM.
 */

abstract class BaseWizardFragment(tagForLog: String) : BaseFragment(tagForLog) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    /**
     * This method performs validation checks on all input fields and then save them as user preferences.
     * It returns true only when all inputs are saved successfully.
     */
    abstract fun saveInputs(): Boolean

    /**
     * Clear error messages for input fields validated previously.
     */
    abstract fun clearErrors()
}