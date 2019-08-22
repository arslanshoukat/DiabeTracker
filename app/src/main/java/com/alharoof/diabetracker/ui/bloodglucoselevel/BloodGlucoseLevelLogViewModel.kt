package com.alharoof.diabetracker.ui.bloodglucoselevel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alharoof.diabetracker.data.base.Result
import com.alharoof.diabetracker.data.bloodglucoselevel.db.BloodGlucoseLevel
import com.alharoof.diabetracker.domain.bloodglucoselevel.LoadBloodGlucoseLevelsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BloodGlucoseLevelLogViewModel @Inject constructor(
    private val loadBloodGlucoseLevelsUseCase: LoadBloodGlucoseLevelsUseCase
) : ViewModel() {

    companion object {
        const val TAG = "BglLogViewModel"
    }

    private val _bglList: MutableLiveData<Result<List<BloodGlucoseLevel>>> = MutableLiveData()
    val bglList get() = _bglList as LiveData<Result<List<BloodGlucoseLevel>>>

    init {
        loadBloodGlucoseLevels()
    }

    private fun loadBloodGlucoseLevels() {
        loadBloodGlucoseLevelsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    Log.d(TAG, "onComplete")
                },
                onError = { throwable ->
                    Log.d(TAG, "onError")
                    _bglList.value = Result.Error("Failed to get log.", throwable)
                },
                onNext = { data ->
                    Log.d(TAG, "onNext")
                    _bglList.value = Result.Success(data)
                }
            )
    }
}
