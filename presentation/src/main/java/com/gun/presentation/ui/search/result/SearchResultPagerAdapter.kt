package com.gun.presentation.ui.search.result

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SearchResultPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val dataList: List<String>,
) : FragmentStateAdapter(fragmentManager, lifecycle), TabLayoutMediator.TabConfigurationStrategy {

    private val fragmentList: List<Fragment>

    init {
        fragmentList = dataList.map { SearchResultFragment.newInstance(it) }
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
        tab.text = dataList[position]
    }
}