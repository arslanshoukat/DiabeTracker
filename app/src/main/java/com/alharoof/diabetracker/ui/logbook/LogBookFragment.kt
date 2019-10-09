package com.alharoof.diabetracker.ui.logbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.data.base.Result
import com.alharoof.diabetracker.data.base.Result.Loading
import com.alharoof.diabetracker.data.base.Result.Success
import com.alharoof.diabetracker.data.logbook.db.LogEntry
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.log_book_fragment.progressBar
import kotlinx.android.synthetic.main.log_book_fragment.rvLogEntries
import kotlinx.android.synthetic.main.log_book_fragment.tvEmptyMessage
import javax.inject.Inject

class LogBookFragment : DaggerFragment() {

    companion object {
        fun newInstance() = LogBookFragment()
        const val TAG = "LogBookFragment"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var bookViewModel: LogBookViewModel
    private val logEntriesAdapter = LogEntriesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.log_book_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bookViewModel = ViewModelProviders.of(this, viewModelFactory).get(LogBookViewModel::class.java)

        activity?.let { act -> act.title = act.resources.getString(R.string.title_log_book) }

        setObservers()
        setLogEntriesAdapter()

        //  fixme : load when viewmodel is initialized
        bookViewModel.loadLogEntries()
    }

    private fun setLogEntriesAdapter() {
        rvLogEntries.itemAnimator = DefaultItemAnimator()
        rvLogEntries.adapter = logEntriesAdapter
    }

    private fun setObservers() {
        bookViewModel.logEntries.observe(viewLifecycleOwner, Observer<Result<List<LogEntry>>> { result ->
            when (result) {
                is Loading -> {
                    tvEmptyMessage.visibility = View.GONE
                    rvLogEntries.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                }
                is Success -> {
                    progressBar.visibility = View.GONE
                    tvEmptyMessage.visibility = View.GONE
                    rvLogEntries.visibility = View.VISIBLE

                    val list = mutableListOf<Any>()

                    result.data?.let {
                        var currentDay: Int? = null
                        for (i in it.indices) {
                            currentDay = it[i].dateTime.dayOfMonth
                            //  if item is first one or date of current entry is different from previous one
                            if (i == 0 || currentDay != it[i - 1].dateTime.dayOfMonth) {
                                //  add date header
                                list.add(it[i].dateTime)
                            }
                            //  add current log entry
                            list.add(it[i])
                        }
                    }

                    logEntriesAdapter.updateLogEntries(list)
                }
                is Error -> {
                    progressBar.visibility = View.GONE
                    rvLogEntries.visibility = View.GONE
                    tvEmptyMessage.visibility = View.VISIBLE
                    tvEmptyMessage.text = result.message
                }
            }
        })
    }
}
