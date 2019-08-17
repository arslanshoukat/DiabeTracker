package com.alharoof.diabetracker.di

import com.alharoof.diabetracker.MainActivity
import com.alharoof.diabetracker.di.main.MainFragmentsModule
import com.alharoof.diabetracker.di.main.MainViewModelsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainFragmentsModule::class, MainViewModelsModule::class])
    abstract fun contributeMainActivity(): MainActivity
}