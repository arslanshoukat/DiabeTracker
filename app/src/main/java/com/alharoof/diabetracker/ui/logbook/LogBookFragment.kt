package com.alharoof.diabetracker.ui.logbook

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.data.base.Result
import com.alharoof.diabetracker.data.base.Result.Loading
import com.alharoof.diabetracker.data.base.Result.Success
import com.alharoof.diabetracker.data.logbook.db.LogEntry
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.log_book_fragment.tvData
import javax.inject.Inject

class LogBookFragment : DaggerFragment() {

    companion object {
        fun newInstance() = LogBookFragment()
        const val TAG = "LogBookFragment"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var bookViewModel: LogBookViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.log_book_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bookViewModel = ViewModelProviders.of(this, viewModelFactory).get(LogBookViewModel::class.java)
        setObservers()
        //  fixme : load when viewmodel is initialized
        bookViewModel.loadLogEntries()
    }

    private fun setObservers() {
        bookViewModel.logEntries.observe(viewLifecycleOwner, Observer<Result<List<LogEntry>>> {
            when (it) {
                is Loading -> {
                }
                is Success -> {
                    val successMsg = it.data?.toString() ?: "Success"
                    Log.d(TAG, successMsg)
                    tvData.text = successMsg
                }
                is Error -> {
                    Log.d(TAG, it.message ?: "Error")
                }
            }
        })
    }
}
