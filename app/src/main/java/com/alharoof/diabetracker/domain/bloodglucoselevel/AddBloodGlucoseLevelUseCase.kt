package com.alharoof.diabetracker.domain.bloodglucoselevel

import com.alharoof.diabetracker.data.bloodglucoselevel.BloodGlucoseLevelRepository
import com.alharoof.diabetracker.data.bloodglucoselevel.db.BloodGlucoseLevel
import com.alharoof.diabetracker.domain.base.CompletableUseCaseWithParameter
import io.reactivex.Completable

class AddBloodGlucoseLevelUseCase(
    private val bloodGlucoseLevelRepository: BloodGlucoseLevelRepository
) : CompletableUseCaseWithParameter<BloodGlucoseLevel> {

    override fun execute(bloodGlucoseLevel: BloodGlucoseLevel): Completable {
        return bloodGlucoseLevelRepository.addBloodGlucoseLevel(bloodGlucoseLevel)
    }
}
