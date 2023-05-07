package com.gun.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gun.domain.model.Event
import com.gun.domain.model.TestData
import com.gun.mvvm_cleanarchitecture.R
import com.gun.mvvm_cleanarchitecture.databinding.FragmentHomeBinding
import com.gun.presentation.ui.home.banner.HomeBannerAdapter
import com.gun.presentation.ui.home.banner.HomeBannerFragment
import com.gun.presentation.ui.home.list.HomeListRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var viewPagerAdapter: HomeBannerAdapter
    private lateinit var homeListRecyclerAdapter: HomeListRecyclerAdapter

    private val homeViewModel: HomeViewModel by viewModels()

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
            viewPagerAdapter = HomeBannerAdapter(requireActivity())
            viewPager.adapter = viewPagerAdapter
            dotsIndicator.attachTo(binding.viewPager)

            homeListRecyclerAdapter = HomeListRecyclerAdapter()
            recyclerView.adapter = homeListRecyclerAdapter

            homeListRecyclerAdapter.submitList(getTestData())
        }

        initObserver()

        homeViewModel.getHomeListData()
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    homeViewModel.homeUiStateFlow.collect {
                        when (it) {
                            is HomeUiState.ShowLoading -> {
                                // TODO Show Loading
                            }
                            is HomeUiState.ShowMessage -> {
                                // TODO Show Message
                            }
                            is HomeUiState.ShowData -> {
                                setHomeBannerViewPager(it.data.eventList ?: emptyList())
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setHomeBannerViewPager(data: List<Event>) {
        if (data.isEmpty()) {
            return
        }

        val fragmentLis = data.let {
            it.slice(1..5).map { event ->
                HomeBannerFragment.newInstance(event)
            }
        }

        viewPagerAdapter.replaceFragmentList(fragmentLis)
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
}