package com.alharoof.diabetracker.domain.report

import com.alharoof.diabetracker.data.logbook.LogEntryRepository
import com.alharoof.diabetracker.data.logbook.db.LogEntry
import com.alharoof.diabetracker.domain.base.UseCase
import io.reactivex.Observable

class LoadWeeklyLogEntriesUseCase(
    private val logEntryRepository: LogEntryRepository
) : UseCase<List<LogEntry>> {

    override fun execute(): Observable<List<LogEntry>> {
        return logEntryRepository.getBglLogEntriesWithCount(7)
    }
}
