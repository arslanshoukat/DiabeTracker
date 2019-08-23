package com.alharoof.diabetracker.ui.bloodglucoselevel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alharoof.diabetracker.data.base.Result
import com.alharoof.diabetracker.data.bloodglucoselevel.db.BloodGlucoseLevel
import com.alharoof.diabetracker.domain.bloodglucoselevel.LoadBloodGlucoseLevelsUseCase
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
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

    lateinit var disposable: Disposable

    init {
//        loadBloodGlucoseLevels()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun loadBloodGlucoseLevels() {
        loadBloodGlucoseLevelsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<BloodGlucoseLevel>> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onComplete() {
                }

                override fun onNext(data: List<BloodGlucoseLevel>) {
                    _bglList.value = Result.Success(data)
                }

                override fun onError(throwable: Throwable) {
                    _bglList.value = Result.Error("Failed to get log.", throwable)
                }
            })
    }
}