package com.alharoof.diabetracker.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alharoof.diabetracker.data.logbook.model.DateTimeRange
import com.alharoof.diabetracker.domain.main.LoadAverageBglWithinUseCase
import com.alharoof.diabetracker.domain.main.LoadLogEntriesTotalCountUseCase
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

/**
 * Created by Arslan Shoukat on Oct 17, 2019 11:59 PM.
 */
class MainViewModel @Inject constructor(
    private val loadAverageBglWithinUseCase: LoadAverageBglWithinUseCase,
    private val loadLogEntriesTotalCountUseCase: LoadLogEntriesTotalCountUseCase
) : ViewModel() {

    private val _averageBgl = MutableLiveData<Int>()
    val averageBgl = _averageBgl as LiveData<Int>
    private val _logEntriesTotalCount = MutableLiveData<Int>()
    val logEntriesTotalCount = _logEntriesTotalCount as LiveData<Int>

    init {
        loadAverageBglForPreviousMonths(OffsetDateTime.now(), 1)
        loadLogEntriesTotalCount()
    }

    private fun loadAverageBglForPreviousMonths(currentDateTime: OffsetDateTime, months: Long) {
        loadAverageBglWithinUseCase.execute(
            DateTimeRange(
                startDateTime = currentDateTime.minusMonths(months),
                endDateTime = currentDateTime
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Float> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {}

                override fun onNext(aveBgl: Float) {
                    _averageBgl.value = aveBgl.toInt()
                }

                override fun onError(e: Throwable) {}
            })
    }

    private fun loadLogEntriesTotalCount() {
        loadLogEntriesTotalCountUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {}

                override fun onNext(totalCount: Int) {
                    _logEntriesTotalCount.value = totalCount
                }

                override fun onError(e: Throwable) {}
            })
    }
}