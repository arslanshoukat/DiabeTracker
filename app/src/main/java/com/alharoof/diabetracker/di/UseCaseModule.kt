package com.alharoof.diabetracker.di

import com.alharoof.diabetracker.data.bloodglucoselevel.BloodGlucoseLevelRepository
import com.alharoof.diabetracker.domain.bloodglucoselevel.AddBloodGlucoseLevelUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    internal fun provideAddBloodGlucoseLevelUseCase(bloodGlucoseLevelRepository: BloodGlucoseLevelRepository)
            : AddBloodGlucoseLevelUseCase = AddBloodGlucoseLevelUseCase(bloodGlucoseLevelRepository)
}