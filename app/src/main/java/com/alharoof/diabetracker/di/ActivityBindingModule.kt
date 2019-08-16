package com.alharoof.diabetracker.di

import com.alharoof.diabetracker.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("UNUSED")
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeMainActivity(): MainActivity
}