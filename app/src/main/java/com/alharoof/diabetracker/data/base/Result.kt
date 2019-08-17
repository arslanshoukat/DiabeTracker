package com.alharoof.diabetracker.data.base

import com.alharoof.diabetracker.data.base.Result.Success

/**
 * Wrapper class to store data and status of requested data.
 * @param <T>
 * Created by Arslan Shoukat on Aug 17, 2019 10:30 AM.
 */

sealed class Result<T>(
    val data: T? = null,
    val message: String? = null,
    val throwable: Throwable? = null
) {

    class Success<T>(data: T) : Result<T>(data)
    class Error<T>(throwable: Throwable, message: String? = null, data: T? = null) :
        Result<T>(data, message, throwable)

    class Loading<T>(data: T? = null) : Result<T>(data)

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$throwable]"
            is Loading -> "Loading"
        }
    }
}

fun Result<*>.succeeded() = this is Success && data != null