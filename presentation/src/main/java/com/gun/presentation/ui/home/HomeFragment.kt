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
import com.gun.mvvm_cleanarchitecture.R
import com.gun.mvvm_cleanarchitecture.databinding.FragmentHomeBinding
import com.gun.presentation.ui.home.banner.HomeBannerAdapter
import com.gun.presentation.ui.home.banner.HomeBannerFragment
import com.gun.presentation.ui.home.list.HomeMainRecyclerAdapter
import com.gun.presentation.ui.home.model.EventType
import com.gun.presentation.ui.home.model.HomeUiModel
import com.gun.presentation.ui.home.model.HomeUiSubModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var viewPagerAdapter: HomeBannerAdapter

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

        with(binding) {
            viewPagerAdapter = HomeBannerAdapter(requireActivity())
            viewPager.adapter = viewPagerAdapter

            dotsIndicator.attachTo(binding.viewPager)
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
                                if (it.loadingCount >= 1) {                                    binding.shimmerViewContainer.visibility = View.VISIBLE
                                    binding.shimmerViewContainer.startShimmer()
                                    binding.shimmerViewContainer.visibility = View.VISIBLE
                                } else {
                                    binding.shimmerViewContainer.visibility = View.GONE
                                    binding.shimmerViewContainer.stopShimmer()
                                }
                            }
                            is HomeUiState.ShowMessage -> {
                                // TODO Show Message
                            }
                            is HomeUiState.ShowData -> {
                                setHomeBannerViewPager(it.data.fromUiModelType(EventType))
                                setHomeListRecyclerView(it.data)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setHomeBannerViewPager(data: HomeUiSubModel) {
        val thumbnailAvailableList = data.homeListItem?.filter { it.isThumbnailAvailable() }

        if (thumbnailAvailableList.isNullOrEmpty()) {
            binding.recyclerView.visibility = View.GONE
            return
        }

        val fragmentLis = with(thumbnailAvailableList) {
            if (this.size > 5) {
                slice(1..5).map { HomeBannerFragment.newInstance(it) }
            } else {
                map { HomeBannerFragment.newInstance(it) }
            }
        }

        viewPagerAdapter.replaceFragmentList(fragmentLis)
    }

    private fun setHomeListRecyclerView(data: HomeUiModel) {
        binding.recyclerView.adapter = HomeMainRecyclerAdapter(requireContext(), data)
    }
}