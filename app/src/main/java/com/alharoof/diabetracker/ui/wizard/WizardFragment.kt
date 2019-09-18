package com.alharoof.diabetracker.ui.wizard

import com.alharoof.diabetracker.base.BaseFragment

/**
 * Created by Arslan Shoukat on Sep 18, 2019 9:29 AM.
 */

abstract class WizardFragment(tagForLog: String) : BaseFragment(tagForLog) {

    abstract fun saveInputs()
}