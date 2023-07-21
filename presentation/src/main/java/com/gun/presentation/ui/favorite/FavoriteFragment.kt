package com.gun.presentation.ui.favorite

import android.content.res.Configuration
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.gun.domain.common.ContentType
import com.gun.domain.model.favorite.Favorite
import com.gun.mvvm_cleanarchitecture.R
import com.gun.mvvm_cleanarchitecture.databinding.FragmentFavoriteBinding
import com.gun.presentation.common.BaseFragment
import com.gun.presentation.common.ItemClickListener
import com.gun.presentation.ui.common.dialog.FilterBottomSheetDialog
import com.gun.presentation.ui.favorite.list.FavoriteRecyclerAdapter
import com.gun.presentation.ui.favorite.model.FavoriteUiEvent
import com.gun.presentation.ui.favorite.model.FavoriteUiModelState
import com.gun.presentation.ui.favorite.model.FilterItem
import com.gun.presentation.ui.favorite.util.FilterUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : BaseFragment(), ItemClickListener<Favorite>, OnClickListener {

    private lateinit var binding: FragmentFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private val recyclerAdapter = FavoriteRecyclerAdapter(this@FavoriteFragment)

    companion object {
        const val SPAN_COUNT_OF_HORIZONTAL_MODE = 3
        const val SPAN_COUNT_OF_LANDSCAPE_MODE = 7
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            recyclerView.adapter = recyclerAdapter
            btnFilter.setOnClickListener(this@FavoriteFragment)
        }

        applyRecyclerViewSpanCount(Resources.getSystem().configuration)

        initObserver()
    }

    private fun applyRecyclerViewSpanCount(newConfig: Configuration) {
        val layoutManager = binding.recyclerView.layoutManager

        if (layoutManager !is GridLayoutManager) return

        layoutManager.spanCount = if (newConfig.orientation == ORIENTATION_LANDSCAPE) {
            SPAN_COUNT_OF_LANDSCAPE_MODE
        } else {
            SPAN_COUNT_OF_HORIZONTAL_MODE
        }
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    favoriteViewModel.favoriteUiEventSharedFlow.collect {
                        when (it) {
                            is FavoriteUiEvent.ShowLoading -> {
                                binding.shimmerViewContainer.startShimmer()
                                binding.shimmerViewContainer.visibility = View.VISIBLE
                                binding.recyclerView.visibility = View.GONE
                            }

                            is FavoriteUiEvent.HideLoading -> {
                                binding.shimmerViewContainer.visibility = View.GONE
                                binding.shimmerViewContainer.stopShimmer()
                            }

                            is FavoriteUiEvent.ShowBadResult -> {
                                binding.recyclerView.visibility = View.GONE
                                binding.viewBadResult.show(it.badResultType)
                            }

                            is FavoriteUiEvent.HideBadResult -> {
                                binding.recyclerView.visibility = View.VISIBLE
                                binding.viewBadResult.hide()
                            }
                        }
                    }
                }

                launch {
                    favoriteViewModel.favoriteUiStateFlow.collect {
                        when (it) {
                            is FavoriteUiModelState.Nothing -> {}

                            is FavoriteUiModelState.ShowData -> {
                                if (it.data.isNotEmpty()) {
                                    binding.recyclerView.visibility = View.VISIBLE
                                    recyclerAdapter.submitList(it.data)
                                    setFadeAnimation(binding.root, binding.recyclerView.id)
                                }
                            }
                        }
                    }
                }

                launch {
                    favoriteViewModel.selectedFavoriteFilter.collect {
                        val filter = favoriteViewModel.selectedFavoriteFilter.value
                        binding.btnFilter.text = filter.name()
                        requestFavoriteList(filter.parseToContentType())
                    }
                }

            }
        }
    }

    private fun requestFavoriteList(contentType: ContentType?) {
        favoriteViewModel.requestFavoriteList(contentType)
    }

    private fun moveToDetailFragment(contentId: Int, contentType: ContentType) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(contentId, contentType)
        findNavController().navigate(action)
    }

    private fun showFilterDialog() {
        val selectedFilter = favoriteViewModel.selectedFavoriteFilter.value
        val filterItemList = FilterUtils.getFilterItemList(resources, selectedFilter)

        val filterBottomSheetDialog = FilterBottomSheetDialog(requireActivity())
            .submitData(filterItemList)
            .setItemClickListener(object: ItemClickListener<FilterItem> {
                override fun onClickItem(data: FilterItem) {
                    favoriteViewModel.selectedFavoriteFilter.value = data.favoriteUiFilterState
                }
            })

        filterBottomSheetDialog.show()
    }

    override fun onClickItem(data: Favorite) {
        val contentId = data.id
        val contentType = data.contentType
        moveToDetailFragment(contentId, contentType)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_filter -> showFilterDialog()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        applyRecyclerViewSpanCount(newConfig)
    }

}