package com.alharoof.diabetracker.data.logbook

import com.alharoof.diabetracker.data.logbook.db.LogEntry
import com.alharoof.diabetracker.data.logbook.db.LogEntryDao
import io.reactivex.Completable
import io.reactivex.Observable
import org.threeten.bp.OffsetDateTime

class LogEntryRepositoryImpl(
    private val logEntryDao: LogEntryDao
) : LogEntryRepository {

    override fun getBglWithin(from: OffsetDateTime, to: OffsetDateTime): Observable<List<LogEntry>> {
        return logEntryDao.getBglForDateTimeRange(from, to)
    }

    override fun getLastBgl(): Observable<LogEntry> {
        return logEntryDao.getLastBgl()
    }

    override fun getLastMedication(): Observable<LogEntry> {
        return logEntryDao.getLastMedication()
    }

    override fun getLastCarbIntake(): Observable<LogEntry> {
        return logEntryDao.getLastCarbIntake()
    }

    override fun getAllLogEntries(): Observable<List<LogEntry>> {
        return logEntryDao.getAll()
    }

    override fun getAllLogEntriesWithCount(count: Int): Observable<List<LogEntry>> {
        return logEntryDao.getAllWithCount(count)
    }

    override fun getBglLogEntriesWithCount(count: Int): Observable<List<LogEntry>> {
        return logEntryDao.getBglWithCount(count)
    }

    override fun addLogEntry(logEntry: LogEntry): Completable {
        return logEntryDao.insert(logEntry)
    }
}
