package com.alharoof.diabetracker.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.util.navigationItemBackground
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.main_activity.drawerLayout
import kotlinx.android.synthetic.main.main_activity.navView
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var tvAverageBgl: TextView
    private lateinit var tvTotalLogEntries: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_logbook, R.id.nav_reports, R.id.nav_calculator,
                R.id.nav_wizard, R.id.nav_import_export, R.id.nav_about, R.id.nav_settings
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.apply {
            itemBackground = navigationItemBackground(context)
            setupWithNavController(navController)
        }

        //  find views from nav view first header hierarchy which was added in xml
        tvAverageBgl = navView.getHeaderView(0).findViewById(R.id.tvAverageBgl)
        tvTotalLogEntries = navView.getHeaderView(0).findViewById(R.id.tvTotalLogEntries)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        setObservers()
    }

    private fun setObservers() {
        viewModel.averageBgl.observe(this, Observer<Int> {
            tvAverageBgl.text = if (it == 0) "-" else "$it"
        })
        viewModel.logEntriesTotalCount.observe(this, Observer<Int> {
            tvTotalLogEntries.text = if (it == 0) "-" else "$it"
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        //  if navigation drawer is opened
        if (drawerLayout.isDrawerOpen(navView)) {
            //  close drawer on back press
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
