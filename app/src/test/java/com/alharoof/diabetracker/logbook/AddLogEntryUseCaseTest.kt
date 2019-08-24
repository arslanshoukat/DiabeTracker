package com.alharoof.diabetracker.logbook

import com.alharoof.diabetracker.data.logbook.LogEntryRepository
import com.alharoof.diabetracker.domain.logbook.AddLogEntryUseCase
import com.alharoof.diabetracker.util.TestData
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
class AddLogEntryUseCaseTest {

    @Mock
    private lateinit var logEntryRepository: LogEntryRepository

    private lateinit var testObserver: TestObserver<Completable>
    private lateinit var addLogEntryUseCase: AddLogEntryUseCase

    @Before
    fun setUp() {
        testObserver = TestObserver()
        addLogEntryUseCase = AddLogEntryUseCase(logEntryRepository)
    }

    @After
    fun tearDown() {
        reset(logEntryRepository)
        testObserver.dispose()
    }

    @Test
    fun `should add new log entry`() {
        `when`(logEntryRepository.addLogEntry(TestData.logEntry)).thenReturn(Completable.complete())

        addLogEntryUseCase.execute(TestData.logEntry).subscribe(testObserver)

        verify(logEntryRepository).addLogEntry(TestData.logEntry)
        verifyNoMoreInteractions(logEntryRepository)

        testObserver.assertComplete()
    }
}