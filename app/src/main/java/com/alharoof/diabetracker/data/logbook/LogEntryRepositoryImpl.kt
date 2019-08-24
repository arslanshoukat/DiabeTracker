package com.alharoof.diabetracker.data.logbook

import com.alharoof.diabetracker.data.logbook.db.LogEntry
import com.alharoof.diabetracker.data.logbook.db.LogEntryDao
import io.reactivex.Completable
import io.reactivex.Observable

class LogEntryRepositoryImpl(
    private val logEntryDao: LogEntryDao
) : LogEntryRepository {

    override fun getAllLogEntries(): Observable<List<LogEntry>> {
        return logEntryDao.getAll()
    }

    override fun addLogEntry(logEntry: LogEntry): Completable {
        return logEntryDao.insert(logEntry)
    }
}
