package com.alharoof.diabetracker.domain.bloodglucoselevel

import com.alharoof.diabetracker.data.bloodglucoselevel.BloodGlucoseLevelRepository
import com.alharoof.diabetracker.data.bloodglucoselevel.db.BloodGlucoseLevel
import com.alharoof.diabetracker.domain.base.UseCase
import io.reactivex.Observable

class LoadBloodGlucoseLevelsUseCase(
    private val bloodGlucoseLevelRepository: BloodGlucoseLevelRepository
) : UseCase<List<BloodGlucoseLevel>> {

    override fun execute(): Observable<List<BloodGlucoseLevel>> {
        return bloodGlucoseLevelRepository.getAllBloodGlucoseLevels()
    }
}
