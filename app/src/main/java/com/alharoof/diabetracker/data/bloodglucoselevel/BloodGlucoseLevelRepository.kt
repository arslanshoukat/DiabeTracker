package com.alharoof.diabetracker.data.bloodglucoselevel

import com.alharoof.diabetracker.data.bloodglucoselevel.db.BloodGlucoseLevel
import io.reactivex.Completable

interface BloodGlucoseLevelRepository {

    fun addBloodGlucoseLevel(bloodGlucoseLevel: BloodGlucoseLevel): Completable
}
