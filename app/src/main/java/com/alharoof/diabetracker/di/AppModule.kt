package com.alharoof.diabetracker.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.alharoof.diabetracker.data.base.AppDatabase
import com.alharoof.diabetracker.data.logbook.LogEntryRepository
import com.alharoof.diabetracker.data.logbook.LogEntryRepositoryImpl
import com.alharoof.diabetracker.data.logbook.db.LogEntryDao
import com.alharoof.diabetracker.data.settings.PrefManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideAppDatabase(appContext: Context): AppDatabase =
        Room.databaseBuilder(appContext, AppDatabase::class.java, "diabetracker-db").build()

    @Singleton
    @Provides
    fun provideLogEntryDao(db: AppDatabase): LogEntryDao = db.logEntryDao()

    @Singleton
    @Provides
    fun provideLogEntryRepository(logEntryDao: LogEntryDao): LogEntryRepository = LogEntryRepositoryImpl(logEntryDao)

    @Singleton
    @Provides
    fun providePrefManager(context: Context): PrefManager = PrefManager(context)
}
