package com.alharoof.diabetracker.di

import com.alharoof.diabetracker.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [FragmentsModule::class, ViewModelsModule::class])
    abstract fun contributeMainActivity(): MainActivity
}