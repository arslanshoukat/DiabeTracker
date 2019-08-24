package com.alharoof.diabetracker.di

import androidx.lifecycle.ViewModel
import com.alharoof.diabetracker.ui.logbook.AddLogEntryViewModel
import com.alharoof.diabetracker.ui.logbook.LogBookViewModel
import com.alharoof.diabetracker.ui.main.MainViewModel
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
}