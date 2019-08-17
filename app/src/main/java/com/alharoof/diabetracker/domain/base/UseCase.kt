package com.alharoof.diabetracker.domain.base

import io.reactivex.Observable

interface UseCase<R> {

    fun execute(): Observable<R>
}
