package com.alharoof.diabetracker.domain.calculator

import com.alharoof.diabetracker.data.logbook.LogEntryRepository
import com.alharoof.diabetracker.data.logbook.model.DateTimeRange
import com.alharoof.diabetracker.domain.base.UseCaseWithParameter
import io.reactivex.Observable

class LoadActiveInsulinWithinUseCase(
    private val logEntryRepository: LogEntryRepository
) : UseCaseWithParameter<DateTimeRange, Int> {

    override fun execute(parameter: DateTimeRange): Observable<Int> {
        return logEntryRepository.getActiveInsulinWithin(parameter.startDateTime, parameter.endDateTime)
    }
}
