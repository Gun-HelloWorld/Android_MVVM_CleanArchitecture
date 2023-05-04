package com.gun.android_marvel_example.ui.home

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.gun.android_marvel_example.R
import com.gun.android_marvel_example.data.TestData
import com.gun.android_marvel_example.databinding.FragmentHomeBinding
import com.gun.android_marvel_example.ui.home.banner.HomeBannerAdapter
import com.gun.android_marvel_example.ui.home.banner.HomeBannerFragment
import com.gun.android_marvel_example.ui.home.banner.HomeBannerPageChangeCallback
import com.gun.android_marvel_example.ui.home.list.HomeListRecyclerAdapter


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var viewPagerAdapter: HomeBannerAdapter
    private lateinit var homeListRecyclerAdapter: HomeListRecyclerAdapter

    private lateinit var pageChangeCallback: OnPageChangeCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewPagerAdapter = HomeBannerAdapter(requireActivity(), getTestFragment())
            pageChangeCallback = HomeBannerPageChangeCallback(binding.viewPager, viewPagerAdapter)
            viewPager.registerOnPageChangeCallback(pageChangeCallback)
            viewPager.adapter = viewPagerAdapter
            dotsIndicator.attachTo(binding.viewPager)

            homeListRecyclerAdapter = HomeListRecyclerAdapter()
            recyclerView.adapter = homeListRecyclerAdapter

            homeListRecyclerAdapter.submitList(getTestData())
        }
    }

    override fun onPause() {
        super.onPause()
        binding.viewPager.unregisterOnPageChangeCallback(pageChangeCallback)
    }

    // TODO Test
    private fun getTestData() = mutableListOf(
        TestData("1", ""),
        TestData("2", ""),
        TestData("3", ""),
        TestData("4", ""),
        TestData("5", ""),
        TestData("6", ""),
        TestData("7", ""),
        TestData("8", ""),
        TestData("9", ""),
        TestData("10", ""),
        TestData("11", ""),
        TestData("12", ""),
        TestData("13", "")
    )

    // TODO Test
    private fun getTestFragment() = mutableListOf(
        HomeBannerFragment.newInstance(),
        HomeBannerFragment.newInstance(),
        HomeBannerFragment.newInstance(),
        HomeBannerFragment.newInstance()
    )
}