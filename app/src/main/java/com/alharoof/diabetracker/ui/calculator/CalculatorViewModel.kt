package com.alharoof.diabetracker.ui.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alharoof.diabetracker.data.logbook.model.DateTimeRange
import com.alharoof.diabetracker.data.settings.PrefManager
import com.alharoof.diabetracker.domain.calculator.LoadActiveInsulinWithinUseCase
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

class CalculatorViewModel @Inject constructor(
    private val loadActiveInsulinWithinUseCase: LoadActiveInsulinWithinUseCase,
    private val prefManager: PrefManager
) : ViewModel() {

    private val _activeInsulin = MutableLiveData<Int>()
    val activeInsulin = _activeInsulin as LiveData<Int>

    init {
        loadActiveInsulinWithin(OffsetDateTime.now(), 60 * 4L)
    }

    private fun loadActiveInsulinWithin(currentDateTime: OffsetDateTime, insulinActiveDurationInMinutes: Long) {
        loadActiveInsulinWithinUseCase.execute(
            DateTimeRange(
                startDateTime = currentDateTime.minusMinutes(insulinActiveDurationInMinutes),
                endDateTime = currentDateTime
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {}

                override fun onNext(actInsulin: Int) {
                    _activeInsulin.value = actInsulin
                }

                override fun onError(e: Throwable) {}
            })
    }

    fun loadInsulinToCarbRatio(): Float {
        return prefManager.getInsulinToCarbRatio()
    }

    fun loadInsulinSensitivityFactor(): Float {
        return prefManager.getInsulinSensitivityFactor()
    }

    fun loadTargetBgl(): Int {
        return prefManager.getTargetBgl()
    }
}
