package com.alharoof.diabetracker.di

import com.alharoof.diabetracker.data.logbook.LogEntryRepository
import com.alharoof.diabetracker.domain.dashboard.LoadLastBglUseCase
import com.alharoof.diabetracker.domain.dashboard.LoadLastCarbIntakeUseCase
import com.alharoof.diabetracker.domain.dashboard.LoadLastMedicationUseCase
import com.alharoof.diabetracker.domain.logbook.AddLogEntryUseCase
import com.alharoof.diabetracker.domain.logbook.LoadLogEntriesUseCase
import com.alharoof.diabetracker.domain.report.LoadWeeklyLogEntriesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    internal fun provideAddLogEntryUseCase(logEntryRepository: LogEntryRepository)
            : AddLogEntryUseCase = AddLogEntryUseCase(logEntryRepository)

    @Provides
    @Singleton
    internal fun provideLoadLogEntriesUseCase(logEntryRepository: LogEntryRepository)
            : LoadLogEntriesUseCase = LoadLogEntriesUseCase(logEntryRepository)

    @Provides
    @Singleton
    internal fun provideLoadLastBglUseCase(logEntryRepository: LogEntryRepository)
            : LoadLastBglUseCase = LoadLastBglUseCase(logEntryRepository)

    @Provides
    @Singleton
    internal fun provideLoadLastMedicationUseCase(logEntryRepository: LogEntryRepository)
            : LoadLastMedicationUseCase = LoadLastMedicationUseCase(logEntryRepository)

    @Provides
    @Singleton
    internal fun provideLoadLastCarbIntakeUseCase(logEntryRepository: LogEntryRepository)
            : LoadLastCarbIntakeUseCase = LoadLastCarbIntakeUseCase(logEntryRepository)

    @Provides
    @Singleton
    internal fun provideLoadWeeklyLogEntriesUseCase(logEntryRepository: LogEntryRepository)
            : LoadWeeklyLogEntriesUseCase = LoadWeeklyLogEntriesUseCase(logEntryRepository)
}