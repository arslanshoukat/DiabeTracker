package com.alharoof.diabetracker.data.logbook

import com.alharoof.diabetracker.data.logbook.db.LogEntry
import io.reactivex.Completable
import io.reactivex.Observable

interface LogEntryRepository {

    fun addLogEntry(logEntry: LogEntry): Completable

    fun getAllLogEntries(): Observable<List<LogEntry>>
}
