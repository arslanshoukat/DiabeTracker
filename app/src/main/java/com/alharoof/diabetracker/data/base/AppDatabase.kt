package com.alharoof.diabetracker.data.base

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alharoof.diabetracker.data.bloodglucoselevel.db.BloodGlucoseLevel
import com.alharoof.diabetracker.data.bloodglucoselevel.db.BloodGlucoseLevelDao
import com.alharoof.diabetracker.util.Converters

@Database(entities = [BloodGlucoseLevel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bloodGlucoseLevelDao(): BloodGlucoseLevelDao
}