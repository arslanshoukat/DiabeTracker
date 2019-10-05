package com.alharoof.diabetracker.ui.wizard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.base.BaseFragment
import kotlinx.android.synthetic.main.wizard_fragment.btnBack
import kotlinx.android.synthetic.main.wizard_fragment.btnNext
import kotlinx.android.synthetic.main.wizard_fragment.tvTitle
import kotlinx.android.synthetic.main.wizard_fragment.vpWizard

class WizardFragment : BaseFragment(TAG) {

    companion object {
        private const val TAG = "WizardFragment"

        fun newInstance() = WizardFragment()
    }

    private lateinit var wizardPagerAdapter: WizardPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.wizard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        wizardPagerAdapter = WizardPagerAdapter(childFragmentManager)
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
                val wizardFragment = wizardPagerAdapter.getItem(currentItem) as? BaseWizardFragment

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
                        //  go to previously opened top destination
                        findNavController().navigateUp()
                    }
                }
            }
        }

        btnBack.setOnClickListener {
            if (vpWizard.currentItem > 0) {
                (wizardPagerAdapter.getItem(vpWizard.currentItem) as? BaseWizardFragment)?.clearErrors()
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

        private val fragmentList: List<BaseWizardFragment> = listOf(
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
