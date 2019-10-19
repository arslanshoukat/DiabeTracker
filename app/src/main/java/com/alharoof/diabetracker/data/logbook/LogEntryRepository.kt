package com.alharoof.diabetracker.data.logbook

import com.alharoof.diabetracker.data.logbook.db.LogEntry
import io.reactivex.Completable
import io.reactivex.Observable
import org.threeten.bp.OffsetDateTime

interface LogEntryRepository {

    fun addLogEntry(logEntry: LogEntry): Completable

    fun getAllLogEntries(): Observable<List<LogEntry>>

    fun getBglWithin(from: OffsetDateTime, to: OffsetDateTime): Observable<List<LogEntry>>

    fun getAllLogEntriesWithCount(count: Int): Observable<List<LogEntry>>

    fun getBglLogEntriesWithCount(count: Int): Observable<List<LogEntry>>

    fun getLastBgl(): Observable<LogEntry>

    fun getLastMedication(): Observable<LogEntry>

    fun getLastCarbIntake(): Observable<LogEntry>

    fun getActiveInsulinWithin(startDateTime: OffsetDateTime, endDateTime: OffsetDateTime): Observable<Int>

    fun getLogEntriesTotalCount(): Observable<Int>

    fun getAverageBglForDateRange(start: OffsetDateTime, end: OffsetDateTime): Observable<Float>
}
