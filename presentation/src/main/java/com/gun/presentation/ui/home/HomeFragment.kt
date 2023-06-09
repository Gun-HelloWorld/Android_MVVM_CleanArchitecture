package com.gun.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.gun.mvvm_cleanarchitecture.R
import com.gun.mvvm_cleanarchitecture.databinding.FragmentHomeBinding
import com.gun.presentation.common.BaseFragment
import com.gun.presentation.common.ItemClickListener
import com.gun.presentation.ui.home.banner.HomeBannerAdapter
import com.gun.presentation.ui.home.list.HomeMainRecyclerAdapter
import com.gun.presentation.ui.home.model.HomeListItem
import com.gun.presentation.ui.home.model.HomeUiModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment(), ItemClickListener<HomeListItem> {

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
            lifecycleOwner = viewLifecycleOwner
            viewPagerAdapter = HomeBannerAdapter(childFragmentManager, lifecycle, this@HomeFragment)
            viewPager.adapter = viewPagerAdapter

            dotsIndicator.attachTo(binding.viewPager)
        }

        initObserver()
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    homeViewModel.homeUiStateFlow.collect {
                        when (it) {
                            is HomeUiModelState.Nothing -> {}
                            is HomeUiModelState.ShowData -> {
                                setHomeBannerViewPager(it.data)
                                setHomeListRecyclerView(it.data)
                                setFadeAnimation(binding.root, *binding.groupHomeContents.referencedIds)
                            }
                        }
                    }
                }

                launch {
                    homeViewModel.loadingStateFlow.collect {
                        if (it >= 1) {
                            binding.shimmerViewContainer.startShimmer()
                            binding.shimmerViewContainer.visibility = View.VISIBLE
                        } else {
                            binding.shimmerViewContainer.visibility = View.GONE
                            binding.shimmerViewContainer.stopShimmer()
                        }
                    }
                }

                launch {
                    homeViewModel.messageSharedFlow.collect {
                        Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun setHomeBannerViewPager(data: HomeUiModel) {
        val bannerList = homeViewModel.getFilterHomeBannerModel(data)

        if (bannerList.isNullOrEmpty()) {
            binding.viewPager.visibility = View.GONE
            binding.dotsIndicator.visibility = View.GONE
            return
        }

        viewPagerAdapter.replaceData(bannerList)
    }

    private fun setHomeListRecyclerView(data: HomeUiModel) {
        binding.recyclerView.adapter = HomeMainRecyclerAdapter(requireContext(), data, this)
    }

    override fun onClickItem(data: HomeListItem) {
        val contentId = data.id
        val contentType = data.getContentType()
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(contentId, contentType)
        findNavController().navigate(action)
    }
}