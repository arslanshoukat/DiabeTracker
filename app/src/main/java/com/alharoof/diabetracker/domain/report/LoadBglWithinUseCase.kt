package com.alharoof.diabetracker.domain.report

import com.alharoof.diabetracker.data.logbook.LogEntryRepository
import com.alharoof.diabetracker.data.logbook.db.LogEntry
import com.alharoof.diabetracker.data.logbook.model.DateTimeRange
import com.alharoof.diabetracker.domain.base.UseCaseWithParameter
import io.reactivex.Observable

class LoadBglWithinUseCase(
    private val logEntryRepository: LogEntryRepository
) : UseCaseWithParameter<DateTimeRange, List<LogEntry>> {

    override fun execute(parameter: DateTimeRange): Observable<List<LogEntry>> {
        return logEntryRepository.getBglWithin(parameter.startDateTime, parameter.endDateTime)
    }
}
