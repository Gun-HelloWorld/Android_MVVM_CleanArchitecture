package com.gun.presentation.ui.search.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import com.google.android.material.snackbar.Snackbar
import com.gun.domain.model.search.SearchResult
import com.gun.mvvm_cleanarchitecture.databinding.FragmentSearchResultBinding
import com.gun.presentation.common.BaseFragment
import com.gun.presentation.common.ItemClickListener
import com.gun.presentation.ui.common.LoadingStateAdapter
import com.gun.presentation.ui.favorite.FavoriteChangedListener
import com.gun.presentation.ui.search.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val KEY_SEARCH_CONTENT_TYPE = "key_search_content_type"

@AndroidEntryPoint
class SearchResultFragment : BaseFragment(), ItemClickListener<SearchResult>, FavoriteChangedListener<SearchResult> {

    private lateinit var binding: FragmentSearchResultBinding
    private lateinit var searchRecyclerAdapter: SearchResultRecyclerAdapter
    private lateinit var contentType: String

    private val searchViewModel: SearchViewModel by viewModels({requireParentFragment()})

    companion object {
        fun newInstance(contentType: String): SearchResultFragment {
            val bundle = Bundle()
            bundle.putString(KEY_SEARCH_CONTENT_TYPE, contentType)

            val fragment = SearchResultFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        contentType = requireArguments().getString(KEY_SEARCH_CONTENT_TYPE)!!

        binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()

        initObserver()
    }

    override fun onResume() {
        super.onResume()
        searchViewModel.getFavoriteList()
    }

    private fun initLayout() {
        searchRecyclerAdapter = SearchResultRecyclerAdapter(itemClickListener = this, favoriteChangedListener = this)

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            recyclerView.adapter = searchRecyclerAdapter.withLoadStateFooter(LoadingStateAdapter())

            viewBadResult.setRetryClickListener(retryClickListener)
        }
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    searchRecyclerAdapter.loadStateFlow.collectLatest {
                        searchViewModel.pagingLoadStateListener.onLoad(it, searchRecyclerAdapter.itemCount)
                    }
                }

                launch {
                    searchViewModel.searchUiEventSharedFlow.collect {
                        when (it) {
                            is SearchUiEvent.ShowLoading -> {
                                binding.loadingBar.visibility = View.VISIBLE
                            }

                            is SearchUiEvent.HideLoading -> {
                                binding.loadingBar.visibility = View.GONE
                            }

                            is SearchUiEvent.ShowBadResult -> {
                                binding.viewBadResult.show(it.badResultType)
                            }

                            is SearchUiEvent.HideBadResult -> {
                                binding.viewBadResult.hide()
                            }
                        }
                    }
                }

                launch {
                    searchViewModel.searchUiDataStateFlow.collect {
                        when (it) {
                            is SearchUiModel.Initialize -> {}

                            is SearchUiModel.Clear -> {
                                searchRecyclerAdapter.submitData(this@SearchResultFragment.lifecycle, PagingData.empty())
                            }

                            is SearchUiModel.ShowData -> {
                                if (searchViewModel.getContentTypeFromTabTitle(contentType) == it.contentType) {
                                    searchRecyclerAdapter.submitData(this@SearchResultFragment.lifecycle, it.data)
                                    setFadeAnimation(binding.root, binding.recyclerView.id)
                                }
                            }
                        }
                    }
                }

                launch {
                    searchViewModel.messageSharedFlow.collect {
                        Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                    }
                }

                launch {
                    searchViewModel.favoriteIdListStateFlow.collect {
                        searchRecyclerAdapter.setFavoriteIdList(it)
                    }
                }
            }
        }
    }

    private val retryClickListener = View.OnClickListener {
        searchViewModel.getSearchPagingData()
    }

    override fun onClickItem(data: SearchResult) {
        val contentId = data.id
        val contentType = searchViewModel.getContentTypeFromTabTitle(contentType)

        contentType?.let {
            searchViewModel.setSearchUiEventSharedFlow(SearchPageMoveEvent.MoveToDetail(contentId, contentType))
        }
    }

    override fun onFavoriteChange(data: SearchResult, isChecked: Boolean) {
        searchViewModel.changeFavoriteStatus(data, isChecked)
    }
}