package com.gun.presentation.ui.home.banner

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class HomeBannerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragmentList: MutableList<Fragment> = mutableListOf()

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    @SuppressLint("NotifyDataSetChanged")
    fun replaceFragmentList(fragmentList: List<Fragment>?) {
        this.fragmentList.clear()

        if (!fragmentList.isNullOrEmpty()) {
            this.fragmentList.addAll(fragmentList)
        }

        notifyDataSetChanged()
    }
}