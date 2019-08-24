package com.alharoof.diabetracker.data.base

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alharoof.diabetracker.data.logbook.db.LogEntry
import com.alharoof.diabetracker.data.logbook.db.LogEntryDao
import com.alharoof.diabetracker.util.Converters

@Database(entities = [LogEntry::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun logEntryDao(): LogEntryDao
}