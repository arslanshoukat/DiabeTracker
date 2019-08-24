package com.alharoof.diabetracker.di

import com.alharoof.diabetracker.ui.logbook.AddLogEntryFragment
import com.alharoof.diabetracker.ui.logbook.LogBookFragment
import com.alharoof.diabetracker.ui.main.MainFragment
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
}