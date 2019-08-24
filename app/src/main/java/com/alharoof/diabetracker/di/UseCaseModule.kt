package com.alharoof.diabetracker.di

import com.alharoof.diabetracker.data.logbook.LogEntryRepository
import com.alharoof.diabetracker.domain.logbook.AddLogEntryUseCase
import com.alharoof.diabetracker.domain.logbook.LoadLogEntriesUseCase
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
}