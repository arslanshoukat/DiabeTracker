package com.alharoof.diabetracker.domain.dashboard

import com.alharoof.diabetracker.data.logbook.LogEntryRepository
import com.alharoof.diabetracker.data.logbook.db.LogEntry
import com.alharoof.diabetracker.domain.base.UseCase
import io.reactivex.Observable

class LoadLastBglUseCase(
    private val logEntryRepository: LogEntryRepository
) : UseCase<LogEntry> {

    override fun execute(): Observable<LogEntry> {
        return logEntryRepository.getLastBgl()
    }
}