package com.alharoof.diabetracker

import android.os.Bundle
import com.alharoof.diabetracker.ui.bloodglucoselevel.AddBloodGlucoseLevelFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AddBloodGlucoseLevelFragment.newInstance())
                .commitNow()
        }
    }

}
