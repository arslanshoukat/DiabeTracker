package com.alharoof.diabetracker.ui.bloodglucoselevel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alharoof.diabetracker.data.base.Resource
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
                    _insertStatus.value = Resource.Success()
                }

                override fun onSubscribe(d: Disposable) {
                    _insertStatus.value = Resource.Loading()
                }

                override fun onError(e: Throwable) {
                    _insertStatus.value = Resource.Error("Failed to insert")
                }
            })
    }
}
