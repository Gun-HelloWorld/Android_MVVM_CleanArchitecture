package com.gun.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.tabs.TabLayoutMediator
import com.gun.mvvm_cleanarchitecture.R
import com.gun.mvvm_cleanarchitecture.databinding.FragmentSearchBinding
import com.gun.presentation.common.BaseFragment
import com.gun.presentation.ui.search.result.SearchResultPagerAdapter
import kotlinx.coroutines.launch

class SearchFragment : BaseFragment() {

    private lateinit var binding: FragmentSearchBinding

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var viewPagerAdapter: SearchResultPagerAdapter

    private val categoryItemList = mutableListOf("Series", "Comic", "Event", "Character", "Creator")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFadeAnimation(binding.root, binding.tvTitle.id, binding.searchView.id)
        initLayout()
        initObserver()
    }

    private fun initLayout() {
        viewPagerAdapter = SearchResultPagerAdapter(childFragmentManager, lifecycle, categoryItemList)

        with(binding) {
            viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(tabLayout, viewPager, true, true, viewPagerAdapter).attach()
        }
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    for ((i, value) in categoryItemList.withIndex()) {
                        binding.tabLayout.getTabAt(i)?.text = value + i
                    }
                }
            }
        }
    }

}