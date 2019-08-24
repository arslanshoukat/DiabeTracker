package com.alharoof.diabetracker.ui.logbook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alharoof.diabetracker.data.base.Result
import com.alharoof.diabetracker.data.logbook.db.LogEntry
import com.alharoof.diabetracker.domain.logbook.LoadLogEntriesUseCase
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LogBookViewModel @Inject constructor(
    private val loadLogEntriesUseCase: LoadLogEntriesUseCase
) : ViewModel() {

    companion object {
        const val TAG = "LogBookViewModel"
    }

    private val _logEntries: MutableLiveData<Result<List<LogEntry>>> = MutableLiveData()
    val logEntries get() = _logEntries as LiveData<Result<List<LogEntry>>>

    lateinit var disposable: Disposable

    init {
//        loadLogEntries()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun loadLogEntries() {
        loadLogEntriesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<LogEntry>> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onComplete() {
                }

                override fun onNext(data: List<LogEntry>) {
                    _logEntries.value = Result.Success(data)
                }

                override fun onError(throwable: Throwable) {
                    _logEntries.value = Result.Error("Failed to get log.", throwable)
                }
            })
    }
}