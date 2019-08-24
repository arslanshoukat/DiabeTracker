package com.alharoof.diabetracker.logbook

import com.alharoof.diabetracker.data.logbook.LogEntryRepository
import com.alharoof.diabetracker.data.logbook.db.LogEntry
import com.alharoof.diabetracker.domain.logbook.LoadLogEntriesUseCase
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
class LoadLogEntriesUseCaseTest {

    @Mock
    private lateinit var logEntryRepository: LogEntryRepository

    private lateinit var mTestObserver: TestObserver<List<LogEntry>>
    private lateinit var loadLogEntriesUseCase: LoadLogEntriesUseCase

    @Before
    fun setUp() {
        mTestObserver = TestObserver()
        loadLogEntriesUseCase = LoadLogEntriesUseCase(logEntryRepository)
    }

    @After
    fun tearDown() {
        mTestObserver.dispose()
    }

    @Test
    fun `should return all log entries`() {
        `when`(logEntryRepository.getAllLogEntries()).thenReturn(Observable.just(TestData.logEntryList))

        loadLogEntriesUseCase.execute().subscribe(mTestObserver)

        verify(logEntryRepository).getAllLogEntries()
        verifyNoMoreInteractions(logEntryRepository)

        mTestObserver.assertSubscribed()
        mTestObserver.assertNoErrors()
        mTestObserver.assertValue(TestData.logEntryList)
    }
}