package com.alharoof.diabetracker.logbook

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alharoof.diabetracker.data.base.Result
import com.alharoof.diabetracker.domain.logbook.LoadLogEntriesUseCase
import com.alharoof.diabetracker.ui.logbook.LogBookViewModel
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
class LogBookViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var loadLogEntriesUseCase: LoadLogEntriesUseCase

    private lateinit var logBookViewModel: LogBookViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        logBookViewModel = LogBookViewModel(loadLogEntriesUseCase)
    }

    @Test
    fun `should return success and data if log entries loaded successfully`() {
        `when`(loadLogEntriesUseCase.execute()).thenReturn(Observable.just(TestData.logEntryList))

        logBookViewModel.loadLogEntries()

        verify(loadLogEntriesUseCase).execute()
        verifyNoMoreInteractions(loadLogEntriesUseCase)

        val logEntriesResult = LiveDataTestUtil.getValue(logBookViewModel.logEntries)
        assert(logEntriesResult is Result.Success)
        assert(logEntriesResult?.data == TestData.logEntryList)
    }

    @Test
    fun `should return error if log entries failed to load`() {
        `when`(loadLogEntriesUseCase.execute()).thenReturn(Observable.error(Exception()))

        logBookViewModel.loadLogEntries()

        verify(loadLogEntriesUseCase).execute()
        verifyNoMoreInteractions(loadLogEntriesUseCase)

        assert(LiveDataTestUtil.getValue(logBookViewModel.logEntries) is Result.Error)
    }
}