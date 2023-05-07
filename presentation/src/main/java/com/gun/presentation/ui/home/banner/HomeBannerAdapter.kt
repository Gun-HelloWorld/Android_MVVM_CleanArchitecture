package com.gun.presentation.ui.home.banner

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class HomeBannerAdapter(
    fragmentActivity: FragmentActivity,
) : FragmentStateAdapter(fragmentActivity) {
    private val fragmentList: MutableList<Fragment> = mutableListOf()

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    @SuppressLint("NotifyDataSetChanged")
    fun replaceFragmentList(fragmentList: List<Fragment>) {
        this.fragmentList.clear()
        this.fragmentList.addAll(fragmentList)
        notifyDataSetChanged()
    }
}