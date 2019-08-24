package com.alharoof.diabetracker.logbook

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alharoof.diabetracker.data.base.Result
import com.alharoof.diabetracker.domain.logbook.AddLogEntryUseCase
import com.alharoof.diabetracker.ui.logbook.AddLogEntryViewModel
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
class AddLogEntryViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var addLogEntryUseCase: AddLogEntryUseCase

    private lateinit var addLogEntryViewModel: AddLogEntryViewModel

    @Before
    fun setUp() {
        addLogEntryViewModel = AddLogEntryViewModel(addLogEntryUseCase)
    }

    @Test
    fun `should return success if log entry insert was successful`() {
        `when`(addLogEntryUseCase.execute(TestData.logEntry)).thenReturn(Completable.complete())

        addLogEntryViewModel.addLogEntry(TestData.logEntry)

        verify(addLogEntryUseCase).execute(TestData.logEntry)
        verifyNoMoreInteractions(addLogEntryUseCase)

        assert(LiveDataTestUtil.getValue(addLogEntryViewModel.insertStatus) is Result.Success)
    }

    @Test
    fun `should return error if log entry insert failed`() {
        `when`(addLogEntryUseCase.execute(TestData.logEntry))
            .thenReturn(Completable.error(Exception()))

        addLogEntryViewModel.addLogEntry(TestData.logEntry)

        verify(addLogEntryUseCase).execute(TestData.logEntry)
        verifyNoMoreInteractions(addLogEntryUseCase)

        assert(LiveDataTestUtil.getValue(addLogEntryViewModel.insertStatus) is Result.Error)
    }
}