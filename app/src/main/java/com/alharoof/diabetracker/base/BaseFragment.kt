package com.alharoof.diabetracker.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

/**
 * Created by ashoukat on Sep 18, 2019 11:01 AM.
 */

open class BaseFragment(private val TAG: String) : DaggerFragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i(TAG, "Lifecycle onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "Lifecycle onCreate($savedInstanceState)")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.i(TAG, "Lifecycle onCreateView($savedInstanceState)")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i(TAG, "Lifecycle onActivityCreated($savedInstanceState)")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "Lifecycle onStart()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "Lifecycle onSaveInstanceState($outState)")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "Lifecycle onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "Lifecycle onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "Lifecycle onStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG, "Lifecycle onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "Lifecycle onDestroy()")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i(TAG, "Lifecycle onDetach()")
    }
}