package com.alharoof.diabetracker.di.main

import com.alharoof.diabetracker.ui.bloodglucoselevel.AddBloodGlucoseLevelFragment
import com.alharoof.diabetracker.ui.bloodglucoselevel.BloodGlucoseLevelLogFragment
import com.alharoof.diabetracker.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Arslan Shoukat on Aug 17, 2019 11:55 AM.
 */

@Module
abstract class MainFragmentsModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    internal abstract fun contributeAddBloodGlucoseLevelFragment(): AddBloodGlucoseLevelFragment

    @ContributesAndroidInjector
    internal abstract fun contributeBloodGlucoseLevelLogFragment(): BloodGlucoseLevelLogFragment
}