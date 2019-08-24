package com.alharoof.diabetracker.ui.logbook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alharoof.diabetracker.data.base.Result
import com.alharoof.diabetracker.data.base.Result.Error
import com.alharoof.diabetracker.data.base.Result.Loading
import com.alharoof.diabetracker.data.base.Result.Success
import com.alharoof.diabetracker.data.logbook.db.LogEntry
import com.alharoof.diabetracker.domain.logbook.AddLogEntryUseCase
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddLogEntryViewModel @Inject constructor(
    private val addLogEntryUseCase: AddLogEntryUseCase
) : ViewModel() {

    private val _insertStatus: MutableLiveData<Result<LogEntry>> = MutableLiveData()
    val insertStatus get() = _insertStatus as LiveData<Result<LogEntry>>

    fun addLogEntry(logEntry: LogEntry) {
        addLogEntryUseCase.execute(logEntry)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onComplete() {
                    setSuccessResponse()
                }

                override fun onSubscribe(d: Disposable) {
                    setLoadingResponse()
                }

                override fun onError(e: Throwable) {
                    setErrorResponse()
                }
            })
    }

    private fun setErrorResponse() {
        _insertStatus.value = Error("Failed to insert")
    }

    private fun setLoadingResponse() {
        _insertStatus.value = Loading()
    }

    private fun setSuccessResponse() {
        _insertStatus.value = Success()
    }
}
