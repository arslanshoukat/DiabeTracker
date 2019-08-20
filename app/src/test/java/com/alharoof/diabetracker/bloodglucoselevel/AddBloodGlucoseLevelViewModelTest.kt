package com.alharoof.diabetracker.bloodglucoselevel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alharoof.diabetracker.RxImmediateSchedulerRule
import com.alharoof.diabetracker.domain.bloodglucoselevel.AddBloodGlucoseLevelUseCase
import com.alharoof.diabetracker.ui.bloodglucoselevel.AddBloodGlucoseLevelViewModel
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
    fun shouldAddBloodGlucoseLevel() {
        `when`(addBloodGlucoseLevelUseCase.execute(TestData.bloodGlucoseLevel)).thenReturn(Completable.complete())

        addBloodGlucoseLevelViewModel.addBloodGlucoseLevel(TestData.bloodGlucoseLevel)

        verify(addBloodGlucoseLevelUseCase).execute(TestData.bloodGlucoseLevel)
        verifyNoMoreInteractions(addBloodGlucoseLevelUseCase)
    }
}