package com.alharoof.diabetracker

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.alharoof.diabetracker.di.DaggerAppComponent
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class DiabeTrackerApp : DaggerApplication() {
    private val activityLifecycleCallbacks = MyActivityLifecycleCallbacks()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this);
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
        //  register callback to view activity lifecycle calls in logcat
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }

    private class MyActivityLifecycleCallbacks : ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Log.i(activity.javaClass.simpleName, "Lifecycle onCreate(${savedInstanceState})")
        }

        override fun onActivityStarted(activity: Activity) {
            Log.i(activity.javaClass.simpleName, "Lifecycle onStart()")
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            Log.i(activity.javaClass.simpleName, "Lifecycle onSaveInstanceState(Bundle)")
        }

        override fun onActivityResumed(activity: Activity) {
            Log.i(activity.javaClass.simpleName, "Lifecycle onResume()")
        }

        override fun onActivityPaused(activity: Activity) {
            Log.i(activity.javaClass.simpleName, "Lifecycle onPause()")
        }

        override fun onActivityStopped(activity: Activity) {
            Log.i(activity.javaClass.simpleName, "Lifecycle onStop()")
        }

        override fun onActivityDestroyed(activity: Activity) {
            Log.i(activity.javaClass.simpleName, "Lifecycle onDestroy()")
        }
    }
}
