package com.alharoof.diabetracker.data.bloodglucoselevel

import com.alharoof.diabetracker.data.bloodglucoselevel.db.BloodGlucoseLevel
import io.reactivex.Completable
import io.reactivex.Observable

interface BloodGlucoseLevelRepository {

    fun addBloodGlucoseLevel(bloodGlucoseLevel: BloodGlucoseLevel): Completable

    fun getAllBloodGlucoseLevels(): Observable<List<BloodGlucoseLevel>>
}
