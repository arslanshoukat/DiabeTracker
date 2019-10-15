package com.alharoof.diabetracker.data.logbook.db

import androidx.room.Dao
import androidx.room.Query
import com.alharoof.diabetracker.data.base.BaseDao
import com.alharoof.diabetracker.data.logbook.model.Category
import io.reactivex.Observable
import org.threeten.bp.OffsetDateTime

@Dao
interface LogEntryDao : BaseDao<LogEntry> {

    @Query("SELECT * FROM LogEntry ORDER BY datetime(dateTime) DESC")
    fun getAll(): Observable<List<LogEntry>>

    @Query("SELECT * FROM LogEntry ORDER BY datetime(dateTime) DESC LIMIT :count")
    fun getAllWithCount(count: Int): Observable<List<LogEntry>>

    @Query("SELECT * FROM LogEntry WHERE date(dateTime) >= date(:start) AND date(dateTime) <= date(:end) ORDER BY datetime(dateTime) ASC")
    fun getBglForDateTimeRange(start: OffsetDateTime, end: OffsetDateTime): Observable<List<LogEntry>>

    @Query("SELECT * FROM LogEntry WHERE bgl IS NOT NULL ORDER BY datetime(dateTime) DESC LIMIT :count")
    fun getBglWithCount(count: Int): Observable<List<LogEntry>>

    @Query("SELECT * FROM LogEntry WHERE bgl IS NOT NULL ORDER BY datetime(dateTime)  DESC LIMIT 1")
    fun getLastBgl(): Observable<LogEntry>

    @Query("SELECT * FROM LogEntry WHERE basal_medCode IS NOT NULL ORDER BY datetime(dateTime) DESC LIMIT 1")
    fun getLastMedication(): Observable<LogEntry>

    @Query("SELECT * FROM LogEntry WHERE carbs IS NOT NULL ORDER BY datetime(dateTime) DESC LIMIT 1")
    fun getLastCarbIntake(): Observable<LogEntry>

    @Query("SELECT * FROM LogEntry WHERE category = :category ORDER BY datetime(dateTime) DESC")
    fun getAllByCategory(category: Category): Observable<List<LogEntry>>

    @Query("DELETE FROM LogEntry")
    fun deleteAll()

    @Query("SELECT SUM(bolus_dose) FROM LogEntry WHERE bolus_medCode IS NOT NULL AND datetime(dateTime) >= datetime(:start) AND datetime(dateTime) <= datetime(:end)")
    fun getActiveInsulinForTimeRange(start: OffsetDateTime, end: OffsetDateTime): Observable<Int>
}
