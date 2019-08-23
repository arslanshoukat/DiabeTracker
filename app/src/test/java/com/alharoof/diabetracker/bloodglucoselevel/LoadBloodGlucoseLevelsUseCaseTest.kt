package com.alharoof.diabetracker.bloodglucoselevel

import com.alharoof.diabetracker.data.bloodglucoselevel.BloodGlucoseLevelRepository
import com.alharoof.diabetracker.data.bloodglucoselevel.db.BloodGlucoseLevel
import com.alharoof.diabetracker.domain.bloodglucoselevel.LoadBloodGlucoseLevelsUseCase
import com.alharoof.diabetracker.util.TestData
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.*

/**
 * Created by ashoukat on Aug 23, 2019 2:13 PM.
 */

@RunWith(MockitoJUnitRunner::class)
class LoadBloodGlucoseLevelsUseCaseTest {

    @Mock
    private lateinit var bloodGlucoseLevelRepository: BloodGlucoseLevelRepository

    private lateinit var testObserver: TestObserver<List<BloodGlucoseLevel>>
    private lateinit var loadBloodGlucoseLevelsUseCase: LoadBloodGlucoseLevelsUseCase

    @Before
    fun setUp() {
        testObserver = TestObserver()
        loadBloodGlucoseLevelsUseCase = LoadBloodGlucoseLevelsUseCase(bloodGlucoseLevelRepository)
    }

    @After
    fun tearDown() {
        testObserver.dispose()
    }

    @Test
    fun `should return all bgl`() {
        `when`(bloodGlucoseLevelRepository.getAllBloodGlucoseLevels()).thenReturn(Observable.just(TestData.bglList))

        loadBloodGlucoseLevelsUseCase.execute().subscribe(testObserver)

        verify(bloodGlucoseLevelRepository).getAllBloodGlucoseLevels()
        verifyNoMoreInteractions(bloodGlucoseLevelRepository)

        testObserver.assertSubscribed()
        testObserver.assertNoErrors()
        testObserver.assertValue(TestData.bglList)
    }
}