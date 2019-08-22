package com.alharoof.diabetracker.di.main

import androidx.lifecycle.ViewModel
import com.alharoof.diabetracker.di.ViewModelKey
import com.alharoof.diabetracker.ui.bloodglucoselevel.AddBloodGlucoseLevelViewModel
import com.alharoof.diabetracker.ui.bloodglucoselevel.BloodGlucoseLevelLogViewModel
import com.alharoof.diabetracker.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Arslan Shoukat on Aug 17, 2019 11:20 AM.
 */

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddBloodGlucoseLevelViewModel::class)
    abstract fun bindAddBloodGlucoseLevelViewModel(viewModel: AddBloodGlucoseLevelViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BloodGlucoseLevelLogViewModel::class)
    abstract fun bindBloodGlucoseLevelLogViewModel(viewModel: BloodGlucoseLevelLogViewModel): ViewModel
}