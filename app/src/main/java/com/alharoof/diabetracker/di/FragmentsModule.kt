package com.alharoof.diabetracker.di

import com.alharoof.diabetracker.ui.logbook.AddLogEntryFragment
import com.alharoof.diabetracker.ui.logbook.LogBookFragment
import com.alharoof.diabetracker.ui.main.DashboardFragment
import com.alharoof.diabetracker.ui.main.MainFragment
import com.alharoof.diabetracker.ui.wizard.PersonalInfoWizardFragment
import com.alharoof.diabetracker.ui.wizard.TargetRangesWizardFragment
import com.alharoof.diabetracker.ui.wizard.TreatmentWizardFragment
import com.alharoof.diabetracker.ui.wizard.UnitsWizardFragment
import com.alharoof.diabetracker.ui.wizard.WizardFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Arslan Shoukat on Aug 17, 2019 11:55 AM.
 */

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    internal abstract fun contributeAddLogEntryFragment(): AddLogEntryFragment

    @ContributesAndroidInjector
    internal abstract fun contributeLogBookFragment(): LogBookFragment

    @ContributesAndroidInjector
    internal abstract fun contributeWizardFragment(): WizardFragment

    @ContributesAndroidInjector
    internal abstract fun contributePersonalInfoWizardFragment(): PersonalInfoWizardFragment

    @ContributesAndroidInjector
    internal abstract fun contributeTargetRangesWizardFragment(): TargetRangesWizardFragment

    @ContributesAndroidInjector
    internal abstract fun contributeTreatmentWizardFragment(): TreatmentWizardFragment

    @ContributesAndroidInjector
    internal abstract fun contributeUnitsWizardFragment(): UnitsWizardFragment

    @ContributesAndroidInjector
    internal abstract fun contributeDashboardFragment(): DashboardFragment
}