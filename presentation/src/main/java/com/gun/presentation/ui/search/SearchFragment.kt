package com.gun.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.gun.mvvm_cleanarchitecture.R
import com.gun.mvvm_cleanarchitecture.databinding.FragmentSearchBinding
import com.gun.presentation.common.BaseFragment
import com.gun.presentation.ui.search.result.SearchResultPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment() {

    private lateinit var binding: FragmentSearchBinding

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var viewPagerAdapter: SearchResultPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFadeAnimation(binding.root, binding.tvTitle.id, binding.searchView.id, binding.tabLayout.id, binding.layoutGuide.id)
        initLayout()
        initObserver()
    }

    private fun initLayout() {
        val categoryItemList = resources.getStringArray(R.array.search_tab_name).toList()
        viewPagerAdapter = SearchResultPagerAdapter(childFragmentManager, lifecycle, categoryItemList)

        with(binding) {
            viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(tabLayout, viewPager, true, true, viewPagerAdapter).attach()
            searchView.setOnQueryTextListener(onQueryTextListener)
            tabLayout.addOnTabSelectedListener(onTabSelectedListener)
        }
    }

    override fun onDestroyView() {
        binding.tabLayout.removeOnTabSelectedListener(onTabSelectedListener)
        super.onDestroyView()
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    searchViewModel.searchUiDataStateFlow.collect {
                        when (it) {
                            is SearchUiModel.Initialize -> {
                                showSearchResultView(false)
                            }
                            is SearchUiModel.Clear -> {}
                            is SearchUiModel.ShowData -> {
                                showSearchResultView(true)
                            }
                        }
                    }
                }

                launch {
                    searchViewModel.searchPageMoveEventSharedFlow.collect {
                        when (it) {
                            is SearchPageMoveEvent.MoveToDetail -> {
                                val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(it.contentId, it.contentType)
                                findNavController().navigate(action)
                            }
                        }
                    }
                }
            }
        }
    }

    private val onQueryTextListener = object: SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            showSearchResultView(true)
            binding.searchView.clearFocus()

            with(searchViewModel) {
                queryStateFlow.value = query
                getSearchPagingData ()
            }

            return false
        }

        override fun onQueryTextChange(query: String): Boolean {
            return false
        }
    }

    private val onTabSelectedListener = object: OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            if (tab == null) return

            with(searchViewModel) {
                val contentType = getContentTypeFromTabTitle(tab.text.toString())

                if (contentType == null || currentContentType.value == contentType) {
                    return@with
                }

                currentContentType.value = contentType
                getSearchPagingData()
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {}

        override fun onTabReselected(tab: TabLayout.Tab?) {}
    }

    private fun showSearchResultView(showSearchResult: Boolean) {
        if (showSearchResult) {
            binding.groupSearchGuide.visibility = View.GONE
            binding.groupSearchResult.visibility = View.VISIBLE
        } else {
            binding.groupSearchGuide.visibility = View.VISIBLE
            binding.groupSearchResult.visibility = View.GONE
        }
    }

}