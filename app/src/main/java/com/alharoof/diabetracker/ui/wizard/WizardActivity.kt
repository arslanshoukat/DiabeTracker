package com.alharoof.diabetracker.ui.wizard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.alharoof.diabetracker.R
import kotlinx.android.synthetic.main.activity_wizard.btnBack
import kotlinx.android.synthetic.main.activity_wizard.btnNext
import kotlinx.android.synthetic.main.activity_wizard.tvTitle
import kotlinx.android.synthetic.main.activity_wizard.vpWizard

class WizardActivity : AppCompatActivity() {
    private lateinit var wizardPagerAdapter: WizardPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wizard)

        wizardPagerAdapter = WizardPagerAdapter(supportFragmentManager)
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
            if (vpWizard.currentItem <= wizardPagerAdapter.count - 2) {
                vpWizard.currentItem = vpWizard.currentItem + 1
            }
        }

        btnBack.setOnClickListener {
            if (vpWizard.currentItem >= 0) {
                vpWizard.currentItem = vpWizard.currentItem - 1
            }
        }
    }

    class WizardPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> UnitsWizardFragment.newInstance()
                1 -> PersonalInfoWizardFragment.newInstance()
                2 -> TreatmentWizardFragment.newInstance()
                else -> TargetRangesWizardFragment.newInstance()
            }
        }

        override fun getCount(): Int = 4
    }
}
