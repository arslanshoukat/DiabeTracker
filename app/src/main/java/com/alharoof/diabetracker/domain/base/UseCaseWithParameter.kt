package com.alharoof.diabetracker.domain.base

import io.reactivex.Observable

interface UseCaseWithParameter<P, R> {

    fun execute(parameter: P): Observable<R>
}
