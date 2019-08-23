package com.alharoof.diabetracker.bloodglucoselevel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alharoof.diabetracker.data.base.Result
import com.alharoof.diabetracker.domain.bloodglucoselevel.AddBloodGlucoseLevelUseCase
import com.alharoof.diabetracker.ui.bloodglucoselevel.AddBloodGlucoseLevelViewModel
import com.alharoof.diabetracker.util.LiveDataTestUtil
import com.alharoof.diabetracker.util.RxImmediateSchedulerRule
import com.alharoof.diabetracker.util.TestData
import io.reactivex.Completable
import org.junit.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.*

/**
 * Created by ashoukat on Aug 20, 2019 12:19 PM.
 */

@RunWith(MockitoJUnitRunner::class)
class AddBloodGlucoseLevelViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var addBloodGlucoseLevelUseCase: AddBloodGlucoseLevelUseCase

    private lateinit var addBloodGlucoseLevelViewModel: AddBloodGlucoseLevelViewModel

    @Before
    fun setUp() {
        addBloodGlucoseLevelViewModel = AddBloodGlucoseLevelViewModel(addBloodGlucoseLevelUseCase)
    }

    @Test
    fun `should return success if insert bgl is successful`() {
        `when`(addBloodGlucoseLevelUseCase.execute(TestData.bloodGlucoseLevel)).thenReturn(Completable.complete())

        addBloodGlucoseLevelViewModel.addBloodGlucoseLevel(TestData.bloodGlucoseLevel)

        verify(addBloodGlucoseLevelUseCase).execute(TestData.bloodGlucoseLevel)
        verifyNoMoreInteractions(addBloodGlucoseLevelUseCase)

        assert(LiveDataTestUtil.getValue(addBloodGlucoseLevelViewModel.insertStatus) is Result.Success)
    }

    @Test
    fun `should return error if insert bgl failed`() {
        `when`(addBloodGlucoseLevelUseCase.execute(TestData.bloodGlucoseLevel))
            .thenReturn(Completable.error(Exception()))

        addBloodGlucoseLevelViewModel.addBloodGlucoseLevel(TestData.bloodGlucoseLevel)

        verify(addBloodGlucoseLevelUseCase).execute(TestData.bloodGlucoseLevel)
        verifyNoMoreInteractions(addBloodGlucoseLevelUseCase)

        assert(LiveDataTestUtil.getValue(addBloodGlucoseLevelViewModel.insertStatus) is Result.Error)
    }
}