package com.alharoof.diabetracker.domain.logbook

import com.alharoof.diabetracker.data.logbook.LogEntryRepository
import com.alharoof.diabetracker.data.logbook.db.LogEntry
import com.alharoof.diabetracker.domain.base.UseCase
import io.reactivex.Observable

class LoadLogEntriesUseCase(
    private val logEntryRepository: LogEntryRepository
) : UseCase<List<LogEntry>> {

    override fun execute(): Observable<List<LogEntry>> {
        return logEntryRepository.getAllLogEntries()
    }
}
