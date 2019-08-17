package com.alharoof.diabetracker.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.alharoof.diabetracker.data.base.AppDatabase
import com.alharoof.diabetracker.data.bloodglucoselevel.BloodGlucoseLevelRepository
import com.alharoof.diabetracker.data.bloodglucoselevel.BloodGlucoseLevelRepositoryImpl
import com.alharoof.diabetracker.data.bloodglucoselevel.db.BloodGlucoseLevelDao
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
    fun provideBloodGlucoseLevelDao(db: AppDatabase): BloodGlucoseLevelDao = db.bloodGlucoseLevelDao()

    @Singleton
    @Provides
    fun provideBloodGlucoseLevelRepository(bloodGlucoseLevelDao: BloodGlucoseLevelDao): BloodGlucoseLevelRepository =
        BloodGlucoseLevelRepositoryImpl(bloodGlucoseLevelDao)
}
