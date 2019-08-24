package com.alharoof.diabetracker.data.logbook.db

import androidx.room.Dao
import androidx.room.Query
import com.alharoof.diabetracker.data.base.BaseDao
import com.alharoof.diabetracker.data.logbook.model.Category
import io.reactivex.Observable

@Dao
interface LogEntryDao : BaseDao<LogEntry> {

    @Query("SELECT * FROM LogEntry ORDER BY dateTime DESC")
    fun getAll(): Observable<List<LogEntry>>

    @Query("SELECT * FROM LogEntry WHERE category = :category ORDER BY dateTime DESC")
    fun getAllByCategory(category: Category): Observable<List<LogEntry>>

    @Query("DELETE FROM LogEntry")
    fun deleteAll()
}
