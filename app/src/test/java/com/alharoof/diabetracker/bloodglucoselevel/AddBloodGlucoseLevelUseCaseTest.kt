package com.alharoof.diabetracker.bloodglucoselevel

import com.alharoof.diabetracker.data.bloodglucoselevel.BloodGlucoseLevelRepository
import com.alharoof.diabetracker.domain.bloodglucoselevel.AddBloodGlucoseLevelUseCase
import io.reactivex.Completable
import io.reactivex.observers.TestObserver
import org.junit.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.*

/**
 * Created by Arslan Shoukat on Aug 20, 2019 9:34 AM.
 */

@RunWith(MockitoJUnitRunner::class)
class AddBloodGlucoseLevelUseCaseTest {

    @Mock
    private lateinit var bloodGlucoseLevelRepository: BloodGlucoseLevelRepository

    private lateinit var testObserver: TestObserver<Completable>
    private lateinit var addBloodGlucoseLevelUseCase: AddBloodGlucoseLevelUseCase

    @Before
    fun setUp() {
        testObserver = TestObserver()
        addBloodGlucoseLevelUseCase = AddBloodGlucoseLevelUseCase(bloodGlucoseLevelRepository)
    }

    @Test
    fun shouldAddBloodGlucoseLevel_ValidData() {
        `when`(bloodGlucoseLevelRepository.addBloodGlucoseLevel(TestData.bloodGlucoseLevel)).thenReturn(Completable.complete())

        addBloodGlucoseLevelUseCase.execute(TestData.bloodGlucoseLevel).subscribe(testObserver)

        verify(bloodGlucoseLevelRepository).addBloodGlucoseLevel(TestData.bloodGlucoseLevel)
        verifyNoMoreInteractions(bloodGlucoseLevelRepository)

        testObserver.assertComplete()
    }
}
