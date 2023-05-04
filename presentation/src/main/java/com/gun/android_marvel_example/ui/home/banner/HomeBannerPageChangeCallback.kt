package com.gun.android_marvel_example.ui.home.banner

import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback

class HomeBannerPageChangeCallback(
    private val pager: ViewPager2,
    private val adapter: FragmentStateAdapter
) : OnPageChangeCallback() {

    private var currentState = 0
    private var currentPos = 0

    override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
    ) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        if (currentState != ViewPager2.SCROLL_STATE_DRAGGING || currentPos != position) return

        if (currentPos == 0) {
            pager.currentItem = adapter.itemCount - 1
        } else if (currentPos == adapter.itemCount - 1) {
            pager.currentItem = 0
        }
    }

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        currentPos = position
    }

    override fun onPageScrollStateChanged(state: Int) {
        super.onPageScrollStateChanged(state)
        currentState = state
    }
}