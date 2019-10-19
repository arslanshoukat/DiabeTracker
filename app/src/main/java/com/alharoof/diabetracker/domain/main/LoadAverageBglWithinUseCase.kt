package com.alharoof.diabetracker.domain.main

import com.alharoof.diabetracker.data.logbook.LogEntryRepository
import com.alharoof.diabetracker.data.logbook.model.DateTimeRange
import com.alharoof.diabetracker.domain.base.UseCaseWithParameter
import io.reactivex.Observable

class LoadAverageBglWithinUseCase(
    private val logEntryRepository: LogEntryRepository
) : UseCaseWithParameter<DateTimeRange, Float> {

    override fun execute(parameter: DateTimeRange): Observable<Float> {
        return logEntryRepository.getAverageBglForDateRange(parameter.startDateTime, parameter.endDateTime)
    }
}
