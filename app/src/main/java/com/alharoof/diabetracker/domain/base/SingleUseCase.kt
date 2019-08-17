package com.alharoof.diabetracker.domain.base

import io.reactivex.Single

interface SingleUseCase<R> {

    fun execute(): Single<R>
}
