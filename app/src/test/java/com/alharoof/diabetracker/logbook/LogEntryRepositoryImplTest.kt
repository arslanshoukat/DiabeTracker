package com.alharoof.diabetracker.logbook

import com.alharoof.diabetracker.data.logbook.LogEntryRepositoryImpl
import com.alharoof.diabetracker.data.logbook.db.LogEntryDao
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
class LogEntryRepositoryImplTest {

    @Mock
    private lateinit var logEntryDao: LogEntryDao

    private lateinit var logEntryRepository: LogEntryRepositoryImpl

    @Before
    fun setUp() {
        logEntryRepository = LogEntryRepositoryImpl(logEntryDao)
    }

    @Test
    fun `should create new log entry if data is valid`() {
        `when`(logEntryDao.insert(TestData.logEntry)).thenReturn(Completable.complete())

        val testObserver = logEntryRepository.addLogEntry(TestData.logEntry).test()

        verify(logEntryDao).insert(TestData.logEntry)
        verifyNoMoreInteractions(logEntryDao)
        testObserver.assertComplete()
        testObserver.assertNoErrors()

        testObserver.dispose()
    }

    @Test
    fun `should not create new log entry if data is invalid`() {
        `when`(logEntryDao.insert(TestData.logEntry))
            .thenReturn(Completable.error(Exception()))

        val testObserver = logEntryRepository.addLogEntry(TestData.logEntry).test()

        verify(logEntryDao).insert(TestData.logEntry)
        verifyNoMoreInteractions(logEntryDao)
        testObserver.assertNotComplete()
        testObserver.assertError(Exception::class.java)

        testObserver.dispose()
    }

    @Test
    fun `should return empty list if no log entry available`() {
        `when`(logEntryDao.getAll()).thenReturn(Observable.just(emptyList()))

        val testObserver = logEntryRepository.getAllLogEntries().test()

        verify(logEntryDao).getAll()
        verifyNoMoreInteractions(logEntryDao)
        testObserver.assertValue(emptyList())

        testObserver.dispose()
    }

    @Test
    fun `should return log entries if data is available`() {
        `when`(logEntryDao.getAll()).thenReturn(Observable.just(TestData.logEntryList))

        val testObserver = logEntryRepository.getAllLogEntries().test()

        verify(logEntryDao).getAll()
        verifyNoMoreInteractions(logEntryDao)
        testObserver.assertValue(TestData.logEntryList)

        testObserver.dispose()
    }
}
