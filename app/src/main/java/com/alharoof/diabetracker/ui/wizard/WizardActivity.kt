package com.alharoof.diabetracker.ui.wizard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.alharoof.diabetracker.MainActivity
import com.alharoof.diabetracker.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_wizard.btnBack
import kotlinx.android.synthetic.main.activity_wizard.btnNext
import kotlinx.android.synthetic.main.activity_wizard.tvTitle
import kotlinx.android.synthetic.main.activity_wizard.vpWizard

class WizardActivity : DaggerAppCompatActivity() {
    private lateinit var wizardPagerAdapter: WizardPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wizard)

        wizardPagerAdapter = WizardPagerAdapter(supportFragmentManager)
        //  Disable swipe
        vpWizard.swipeable = false
        //  Set offscreen page limit to one less than total page count. This optimization increases smoothness of
        //  paging interaction and reduces fragment(page) recreation as all fragments are kept in-memory.
        vpWizard.offscreenPageLimit = wizardPagerAdapter.count - 1
        vpWizard.adapter = wizardPagerAdapter

        setListeners()
    }

    private fun setListeners() {
        vpWizard.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        tvTitle.text = "Select Units"
                        btnNext.text = "Next: Personal Information"
                        //  hide pager back button
                        btnBack.visibility = View.INVISIBLE
                    }
                    1 -> {
                        tvTitle.text = "Enter Personal Information"
                        btnNext.text = "Next: Treatment"
                    }
                    2 -> {
                        tvTitle.text = "Select Type of Treatment"
                        btnNext.text = "Next: Target, Ranges and Ratios"
                    }
                    else -> {
                        tvTitle.text = "Enter Target, Ranges and Ratios"
                        btnNext.text = "Next: Complete"
                    }
                }
            }
        })

        btnNext.setOnClickListener {
            var currentItem = vpWizard.currentItem
            if (currentItem < wizardPagerAdapter.count) {
                val wizardFragment = wizardPagerAdapter.getItem(currentItem) as? WizardFragment

                //  save inputs of visible fragments and check if operation was successful
                if (wizardFragment?.saveInputs() == true) {
                    //  if all fields are validated and saved, open next fragment
                    vpWizard.currentItem = ++currentItem
                    //  clear errors as next fragment only opens after validating and saving inputs
                    wizardFragment.clearErrors()

                    if (currentItem >= 1) {
                        //  show pager back button
                        btnBack.visibility = View.VISIBLE
                    }

                    //  if current fragment is last one in adapter
                    if (currentItem >= wizardPagerAdapter.count) {
                        //  start main activity
                        startActivity(Intent(this, MainActivity::class.java))
                        //  finish this to restrict coming back to this activity when user presses back button
                        finish()
                    }
                }
            }
        }

        btnBack.setOnClickListener {
            if (vpWizard.currentItem > 0) {
                (wizardPagerAdapter.getItem(vpWizard.currentItem) as? WizardFragment)?.clearErrors()
                vpWizard.currentItem--
            }
        }
    }

    /**
     * WizardPagerAdapter provides pages (fragments) displayed in viewpager. These are used to get various
     * information / inputs from user that are stored as user preferences. Appropriate values (units) /
     * experience is provided to user based on these preferences.
     */
    class WizardPagerAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val fragmentList: List<WizardFragment> = listOf(
            UnitsWizardFragment.newInstance(),
            PersonalInfoWizardFragment.newInstance(),
            TreatmentWizardFragment.newInstance(),
            TargetRangesWizardFragment.newInstance()
        )

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int = 4
    }
}
