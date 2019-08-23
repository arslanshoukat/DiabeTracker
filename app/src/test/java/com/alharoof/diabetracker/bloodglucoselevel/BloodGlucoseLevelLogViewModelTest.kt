package com.alharoof.diabetracker.bloodglucoselevel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alharoof.diabetracker.data.base.Result
import com.alharoof.diabetracker.domain.bloodglucoselevel.LoadBloodGlucoseLevelsUseCase
import com.alharoof.diabetracker.ui.bloodglucoselevel.BloodGlucoseLevelLogViewModel
import com.alharoof.diabetracker.util.LiveDataTestUtil
import com.alharoof.diabetracker.util.RxImmediateSchedulerRule
import com.alharoof.diabetracker.util.TestData
import io.reactivex.Observable
import org.junit.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.*

/**
 * Created by ashoukat on Aug 23, 2019 3:50 PM.
 */

@RunWith(MockitoJUnitRunner::class)
class BloodGlucoseLevelLogViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var loadBloodGlucoseLevelsUseCase: LoadBloodGlucoseLevelsUseCase

    private lateinit var bloodGlucoseLevelLogViewModel: BloodGlucoseLevelLogViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        bloodGlucoseLevelLogViewModel = BloodGlucoseLevelLogViewModel(loadBloodGlucoseLevelsUseCase)
    }

    @Test
    fun `should return success and data if data loaded successfully`() {
        `when`(loadBloodGlucoseLevelsUseCase.execute()).thenReturn(Observable.just(TestData.bglList))

        bloodGlucoseLevelLogViewModel.loadBloodGlucoseLevels()

        verify(loadBloodGlucoseLevelsUseCase).execute()
        verifyNoMoreInteractions(loadBloodGlucoseLevelsUseCase)

        val bglListResult = LiveDataTestUtil.getValue(bloodGlucoseLevelLogViewModel.bglList)
        assert(bglListResult is Result.Success)
        assert(bglListResult?.data == TestData.bglList)
    }

    @Test
    fun `should return error if data failed to load`() {
        `when`(loadBloodGlucoseLevelsUseCase.execute()).thenReturn(Observable.error(Exception()))

        bloodGlucoseLevelLogViewModel.loadBloodGlucoseLevels()

        verify(loadBloodGlucoseLevelsUseCase).execute()
        verifyNoMoreInteractions(loadBloodGlucoseLevelsUseCase)

        assert(LiveDataTestUtil.getValue(bloodGlucoseLevelLogViewModel.bglList) is Result.Error)
    }
}