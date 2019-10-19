package com.alharoof.diabetracker.domain.main

import com.alharoof.diabetracker.data.logbook.LogEntryRepository
import com.alharoof.diabetracker.domain.base.UseCase
import io.reactivex.Observable

class LoadLogEntriesTotalCountUseCase(
    private val logEntryRepository: LogEntryRepository
) : UseCase<Int> {

    override fun execute(): Observable<Int> {
        return logEntryRepository.getLogEntriesTotalCount()
    }
}
