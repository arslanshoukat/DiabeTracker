package com.alharoof.diabetracker.bloodglucoselevel

import com.alharoof.diabetracker.data.bloodglucoselevel.BloodGlucoseLevelRepositoryImpl
import com.alharoof.diabetracker.data.bloodglucoselevel.db.BloodGlucoseLevelDao
import com.alharoof.diabetracker.util.TestData
import io.reactivex.Completable
import io.reactivex.Observable
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

    private lateinit var bloodGlucoseLevelRepositoryImpl: BloodGlucoseLevelRepositoryImpl

    @Before
    fun setUp() {
        bloodGlucoseLevelRepositoryImpl = BloodGlucoseLevelRepositoryImpl(bloodGlucoseLevelDao)
    }

    @Test
    fun `should create new bgl if data is valid`() {
        `when`(bloodGlucoseLevelDao.insert(TestData.bloodGlucoseLevel)).thenReturn(Completable.complete())

        val testObserver = bloodGlucoseLevelRepositoryImpl.addBloodGlucoseLevel(TestData.bloodGlucoseLevel).test()

        verify(bloodGlucoseLevelDao).insert(TestData.bloodGlucoseLevel)
        verifyNoMoreInteractions(bloodGlucoseLevelDao)
        testObserver.assertComplete()
        testObserver.assertNoErrors()

        testObserver.dispose()
    }

    @Test
    fun `should not create new bgl if data is invalid`() {
        `when`(bloodGlucoseLevelDao.insert(TestData.bloodGlucoseLevel))
            .thenReturn(Completable.error(Exception()))

        val testObserver = bloodGlucoseLevelRepositoryImpl.addBloodGlucoseLevel(TestData.bloodGlucoseLevel).test()

        verify(bloodGlucoseLevelDao).insert(TestData.bloodGlucoseLevel)
        verifyNoMoreInteractions(bloodGlucoseLevelDao)
        testObserver.assertNotComplete()
        testObserver.assertError(Exception::class.java)

        testObserver.dispose()
    }

    @Test
    fun `should return empty list if no bgl available`() {
        `when`(bloodGlucoseLevelDao.getAll()).thenReturn(Observable.just(emptyList()))

        val testObserver = bloodGlucoseLevelRepositoryImpl.getAllBloodGlucoseLevels().test()

        verify(bloodGlucoseLevelDao).getAll()
        verifyNoMoreInteractions(bloodGlucoseLevelDao)
        testObserver.assertValue(emptyList())

        testObserver.dispose()
    }

    @Test
    fun `should return bgl list if bgl is available`() {
        `when`(bloodGlucoseLevelDao.getAll()).thenReturn(Observable.just(TestData.bglList))

        val testObserver = bloodGlucoseLevelRepositoryImpl.getAllBloodGlucoseLevels().test()

        verify(bloodGlucoseLevelDao).getAll()
        verifyNoMoreInteractions(bloodGlucoseLevelDao)
        testObserver.assertValue(TestData.bglList)

        testObserver.dispose()
    }
}
