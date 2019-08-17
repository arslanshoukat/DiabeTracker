package com.alharoof.diabetracker.domain.base

import io.reactivex.Completable

interface CompletableUseCase {

    fun execute(): Completable
}
