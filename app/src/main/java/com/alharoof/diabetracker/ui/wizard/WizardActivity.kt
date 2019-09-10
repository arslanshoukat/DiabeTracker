package com.alharoof.diabetracker.ui.wizard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.alharoof.diabetracker.R
import kotlinx.android.synthetic.main.activity_wizard.tvTitle
import kotlinx.android.synthetic.main.activity_wizard.vpWizard

class WizardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wizard)

        vpWizard.adapter = WizardPagerAdapter(supportFragmentManager)
        vpWizard.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> tvTitle.text = "Select Units"
                    2 -> tvTitle.text = "Enter Personal Information"
                    3 -> tvTitle.text = "Select Type of Treatment"
                    else -> tvTitle.text = "Enter Target, Ranges and Ratios"
                }
            }
        })
    }

    class WizardPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> UnitsWizardFragment.newInstance()
                2 -> PersonalInfoWizardFragment.newInstance()
                3 -> TreatmentWizardFragment.newInstance()
                else -> TargetRangesWizardFragment.newInstance()
            }
        }

        override fun getCount(): Int = 4
    }
}
