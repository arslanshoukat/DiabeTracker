package com.android.example.github.di

import android.app.Application
import com.alharoof.diabetracker.DiabeTrackerApp
import com.alharoof.diabetracker.di.ActivityBindingModule
import com.alharoof.diabetracker.di.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class,
        ViewModelFactoryModule::class]
)
interface AppComponent : AndroidInjector<DiabeTrackerApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
