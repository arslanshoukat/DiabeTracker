package com.alharoof.diabetracker.data.logbook

import com.alharoof.diabetracker.data.logbook.db.LogEntry
import io.reactivex.Completable
import io.reactivex.Observable

interface LogEntryRepository {

    fun addLogEntry(logEntry: LogEntry): Completable

    fun getAllLogEntries(): Observable<List<LogEntry>>

    fun getAllLogEntriesWithCount(count: Int): Observable<List<LogEntry>>

    fun getBglLogEntriesWithCount(count: Int): Observable<List<LogEntry>>

    fun getLastBgl(): Observable<LogEntry>

    fun getLastMedication(): Observable<LogEntry>

    fun getLastCarbIntake(): Observable<LogEntry>
}
