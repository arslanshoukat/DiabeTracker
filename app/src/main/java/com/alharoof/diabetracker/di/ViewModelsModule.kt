package com.alharoof.diabetracker.di

import androidx.lifecycle.ViewModel
import com.alharoof.diabetracker.ui.logbook.AddLogEntryViewModel
import com.alharoof.diabetracker.ui.logbook.LogBookViewModel
import com.alharoof.diabetracker.ui.main.DashboardViewModel
import com.alharoof.diabetracker.ui.main.MainViewModel
import com.alharoof.diabetracker.ui.wizard.PersonalInfoWizardViewModel
import com.alharoof.diabetracker.ui.wizard.TargetRangesWizardViewModel
import com.alharoof.diabetracker.ui.wizard.TreatmentWizardViewModel
import com.alharoof.diabetracker.ui.wizard.UnitsWizardViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Arslan Shoukat on Aug 17, 2019 11:20 AM.
 */

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddLogEntryViewModel::class)
    abstract fun bindAddLogEntryViewModel(viewModel: AddLogEntryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LogBookViewModel::class)
    abstract fun bindLogBookViewModel(bookViewModel: LogBookViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PersonalInfoWizardViewModel::class)
    abstract fun bindPersonalInfoWizardViewModel(personalInfoWizardViewModel: PersonalInfoWizardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TargetRangesWizardViewModel::class)
    abstract fun bindTargetRangesWizardViewModel(targetRangesWizardViewModel: TargetRangesWizardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TreatmentWizardViewModel::class)
    abstract fun bindTreatmentWizardViewModel(treatmentWizardViewModel: TreatmentWizardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UnitsWizardViewModel::class)
    abstract fun bindUnitsWizardViewModel(unitsWizardViewModel: UnitsWizardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(dashboardViewModel: DashboardViewModel): ViewModel
}