package com.alharoof.diabetracker.bloodglucoselevel

import com.alharoof.diabetracker.data.bloodglucoselevel.BloodGlucoseLevelRepositoryImpl
import com.alharoof.diabetracker.data.bloodglucoselevel.db.BloodGlucoseLevelDao
import io.reactivex.Completable
import io.reactivex.observers.TestObserver
import org.junit.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.*

/**
 * Created by ashoukat on Aug 20, 2019 10:30 AM.
 */

@RunWith(MockitoJUnitRunner::class)
class BloodGlucoseLevelRepositoryImplTest {

    @Mock
    private lateinit var bloodGlucoseLevelDao: BloodGlucoseLevelDao

    private lateinit var testObserver: TestObserver<Completable>
    private lateinit var bloodGlucoseLevelRepositoryImpl: BloodGlucoseLevelRepositoryImpl

    @Before
    fun setUp() {
        testObserver = TestObserver()
        bloodGlucoseLevelRepositoryImpl = BloodGlucoseLevelRepositoryImpl(bloodGlucoseLevelDao)
    }

    @Test
    fun `check insert completes, if dao returns success(complete)`() {
        `when`(bloodGlucoseLevelDao.insert(TestData.bloodGlucoseLevel)).thenReturn(Completable.complete())

        bloodGlucoseLevelRepositoryImpl.addBloodGlucoseLevel(TestData.bloodGlucoseLevel).subscribe(testObserver)

        verify(bloodGlucoseLevelDao).insert(TestData.bloodGlucoseLevel)
        verifyNoMoreInteractions(bloodGlucoseLevelDao)

        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun `check if dao throws exception, bgl insert fails `() {
        `when`(bloodGlucoseLevelDao.insert(TestData.bloodGlucoseLevel))
            .thenReturn(Completable.error(Exception()))

        bloodGlucoseLevelRepositoryImpl.addBloodGlucoseLevel(TestData.bloodGlucoseLevel).subscribe(testObserver)

        verify(bloodGlucoseLevelDao).insert(TestData.bloodGlucoseLevel)
        verifyNoMoreInteractions(bloodGlucoseLevelDao)

        testObserver.assertNotComplete()
        testObserver.assertError(Exception::class.java)
    }
}