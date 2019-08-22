package com.alharoof.diabetracker.data.bloodglucoselevel

import com.alharoof.diabetracker.data.bloodglucoselevel.db.BloodGlucoseLevel
import com.alharoof.diabetracker.data.bloodglucoselevel.db.BloodGlucoseLevelDao
import io.reactivex.Completable
import io.reactivex.Observable

class BloodGlucoseLevelRepositoryImpl(
    private val bloodGlucoseLevelDao: BloodGlucoseLevelDao
) : BloodGlucoseLevelRepository {

    override fun getAllBloodGlucoseLevels(): Observable<List<BloodGlucoseLevel>> {
        return bloodGlucoseLevelDao.getAll()
    }

    override fun addBloodGlucoseLevel(bloodGlucoseLevel: BloodGlucoseLevel): Completable {
        return bloodGlucoseLevelDao.insert(bloodGlucoseLevel)
    }
}
