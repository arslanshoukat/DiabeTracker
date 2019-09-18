package com.alharoof.diabetracker.ui.wizard

import androidx.lifecycle.ViewModelProvider
import com.alharoof.diabetracker.base.BaseFragment
import javax.inject.Inject

/**
 * Created by Arslan Shoukat on Sep 18, 2019 9:29 AM.
 */

abstract class WizardFragment(tagForLog: String) : BaseFragment(tagForLog) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract fun saveInputs()
}