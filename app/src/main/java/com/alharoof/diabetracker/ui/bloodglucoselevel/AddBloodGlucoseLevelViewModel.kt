package com.alharoof.diabetracker.ui.bloodglucoselevel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alharoof.diabetracker.data.base.Resource
import com.alharoof.diabetracker.data.base.Resource.Error
import com.alharoof.diabetracker.data.base.Resource.Loading
import com.alharoof.diabetracker.data.base.Resource.Success
import com.alharoof.diabetracker.data.bloodglucoselevel.db.BloodGlucoseLevel
import com.alharoof.diabetracker.domain.bloodglucoselevel.AddBloodGlucoseLevelUseCase
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddBloodGlucoseLevelViewModel @Inject constructor(
    private val addBloodGlucoseLevelUseCase: AddBloodGlucoseLevelUseCase
) : ViewModel() {

    private val _insertStatus: MutableLiveData<Resource<BloodGlucoseLevel>> = MutableLiveData()
    val insertStatus get() = _insertStatus as LiveData<Resource<BloodGlucoseLevel>>

    fun addBloodGlucoseLevel(bloodGlucoseLevel: BloodGlucoseLevel) {
        addBloodGlucoseLevelUseCase.execute(bloodGlucoseLevel)
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
