package com.alharoof.diabetracker.di

import com.alharoof.diabetracker.MainActivity
import com.alharoof.diabetracker.ui.wizard.WizardActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [FragmentsModule::class, ViewModelsModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentsModule::class, ViewModelsModule::class])
    abstract fun contributeWizardActivity(): WizardActivity
}