package com.alharoof.diabetracker.ui.logbook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alharoof.diabetracker.data.base.Result
import com.alharoof.diabetracker.data.base.Result.Error
import com.alharoof.diabetracker.data.base.Result.Loading
import com.alharoof.diabetracker.data.base.Result.Success
import com.alharoof.diabetracker.data.logbook.db.LogEntry
import com.alharoof.diabetracker.data.logbook.model.BglUnit
import com.alharoof.diabetracker.data.logbook.model.MedicationEnum
import com.alharoof.diabetracker.data.settings.PrefManager
import com.alharoof.diabetracker.domain.logbook.AddLogEntryUseCase
import com.alharoof.diabetracker.util.Converters
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddLogEntryViewModel @Inject constructor(
    private val addLogEntryUseCase: AddLogEntryUseCase,
    private val prefManager: PrefManager
) : ViewModel() {

    private val _insertStatus: MutableLiveData<Result<LogEntry>> = MutableLiveData()
    val insertStatus get() = _insertStatus as LiveData<Result<LogEntry>>

    private val _basalMed: MutableLiveData<MedicationEnum> = MutableLiveData()
    val basalMed get() = _basalMed as LiveData<MedicationEnum>

    private val _bolusMed: MutableLiveData<MedicationEnum> = MutableLiveData()
    val bolusMed get() = _bolusMed as LiveData<MedicationEnum>

    private val _bglUnit: MutableLiveData<BglUnit> = MutableLiveData()
    val bglUnit get() = _bglUnit as LiveData<BglUnit>

    //  todo: add support for unit of carbs
//    private val _unitOfMeasurement: MutableLiveData<UnitOfMeasurement> = MutableLiveData()
//    val unitOfMeasurement get() = _unitOfMeasurement as LiveData<UnitOfMeasurement>

    init {
        loadBasalMed()
        loadBolusMed()
        loadBglUnit()
//        loadUnitOfMeasurement()
    }

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

    fun resetStatus() {
        _insertStatus.value = Loading()
    }

    private fun loadBasalMed() {
        _basalMed.value = Converters.fromMedicationCodeToEnum(prefManager.getBasalInsulin())
    }

    private fun loadBolusMed() {
        _bolusMed.value = Converters.fromMedicationCodeToEnum(prefManager.getBolusInsulin())
    }

    private fun loadBglUnit() {
        _bglUnit.value = Converters.fromBglUnitCodeToEnum(prefManager.getBglUnit())
    }

//    private fun loadUnitOfMeasurement() {
//        _unitOfMeasurement.value = Converters.fromUnitOfMeasurementCodeToEnum(prefManager.getUnitOfMeasurement())
//    }
}
