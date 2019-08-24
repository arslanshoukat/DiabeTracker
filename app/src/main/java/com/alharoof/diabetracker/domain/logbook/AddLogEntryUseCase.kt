package com.alharoof.diabetracker.domain.logbook

import com.alharoof.diabetracker.data.logbook.LogEntryRepository
import com.alharoof.diabetracker.data.logbook.db.LogEntry
import com.alharoof.diabetracker.domain.base.CompletableUseCaseWithParameter
import io.reactivex.Completable

class AddLogEntryUseCase(
    private val logEntryRepository: LogEntryRepository
) : CompletableUseCaseWithParameter<LogEntry> {

    override fun execute(logEntry: LogEntry): Completable {
        return logEntryRepository.addLogEntry(logEntry)
    }
}
