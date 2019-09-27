package com.alharoof.diabetracker.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alharoof.diabetracker.data.logbook.db.LogEntry
import com.alharoof.diabetracker.data.settings.PrefManager
import com.alharoof.diabetracker.domain.dashboard.LoadLastBglUseCase
import com.alharoof.diabetracker.domain.dashboard.LoadLastCarbIntakeUseCase
import com.alharoof.diabetracker.domain.dashboard.LoadLastMedicationUseCase
import com.alharoof.diabetracker.domain.report.LoadWeeklyLogEntriesUseCase
import com.alharoof.diabetracker.util.Converters
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val loadLastBglUseCase: LoadLastBglUseCase,
    private val loadLastMedicationUseCase: LoadLastMedicationUseCase,
    private val loadLastCarbIntakeUseCase: LoadLastCarbIntakeUseCase,
    private val loadWeeklyLogEntriesUseCase: LoadWeeklyLogEntriesUseCase,
    private val prefManager: PrefManager
) : ViewModel() {

    private val _lastBglLogEntry = MutableLiveData<LogEntry>()
    val lastBglLogEntry = _lastBglLogEntry as LiveData<LogEntry>
    private val _lastMedicationLogEntry = MutableLiveData<LogEntry>()
    val lastMedicationLogEntry = _lastMedicationLogEntry as LiveData<LogEntry>
    private val _lastCarbIntakeLogEntry = MutableLiveData<LogEntry>()
    val lastCarbIntakeLogEntry = _lastCarbIntakeLogEntry as LiveData<LogEntry>
    private val _weeklyLogEntries = MutableLiveData<List<LogEntry>>()
    val weeklyLogEntries = _weeklyLogEntries as LiveData<List<LogEntry>>

    init {
        loadLastBgl()
        loadLastMedication()
        loadLastCarbIntake()
        loadWeeklyLogEntries()
    }

    private fun loadLastBgl() {
        loadLastBglUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<LogEntry> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(logEntry: LogEntry) {
                    _lastBglLogEntry.value = logEntry
                }

                override fun onError(e: Throwable) {}
            })
    }

    private fun loadLastMedication() {
        loadLastMedicationUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<LogEntry> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(logEntry: LogEntry) {
                    _lastMedicationLogEntry.value = logEntry
                }

                override fun onError(e: Throwable) {}
            })
    }

    private fun loadLastCarbIntake() {
        loadLastCarbIntakeUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<LogEntry> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(logEntry: LogEntry) {
                    _lastCarbIntakeLogEntry.value = logEntry
                }

                override fun onError(e: Throwable) {}
            })
    }

    private fun loadWeeklyLogEntries() {
        loadWeeklyLogEntriesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<LogEntry>> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(list: List<LogEntry>) {
                    _weeklyLogEntries.value = list
                }

                override fun onError(e: Throwable) {}
            })
    }

    fun getFirstName(): String? {
        return prefManager.getFirstName()
    }

    fun getBglUnitPreference(): String {
        return Converters.fromBglUnitCodeToEnum(prefManager.getBglUnit())?.symbol ?: ""
    }
}
