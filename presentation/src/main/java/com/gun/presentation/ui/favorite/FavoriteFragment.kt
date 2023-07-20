package com.gun.presentation.ui.favorite

import android.content.res.Configuration
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Resources
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
import androidx.recyclerview.widget.GridLayoutManager
import com.gun.domain.common.ContentType
import com.gun.domain.model.favorite.Favorite
import com.gun.mvvm_cleanarchitecture.R
import com.gun.mvvm_cleanarchitecture.databinding.FragmentFavoriteBinding
import com.gun.presentation.common.BaseFragment
import com.gun.presentation.common.ItemClickListener
import com.gun.presentation.ui.favorite.list.FavoriteRecyclerAdapter
import com.gun.presentation.ui.favorite.model.FavoriteUiModelState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : BaseFragment(), ItemClickListener<Favorite> {

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
        }

        applyRecyclerViewSpanCount(Resources.getSystem().configuration)

        initObserver()
    }

    override fun onResume() {
        super.onResume()
        favoriteViewModel.getFavoriteList(null)
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
                    favoriteViewModel.favoriteUiStateFlow.collect {
                        when (it) {
                            is FavoriteUiModelState.Nothing -> {}
                            is FavoriteUiModelState.ShowData -> {
                                recyclerAdapter.submitList(it.data)
                                setFadeAnimation(binding.root, binding.recyclerView.id)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun moveToDetailFragment(contentId: Int, contentType: ContentType) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(contentId, contentType)
        findNavController().navigate(action)
    }

    override fun onClickItem(data: Favorite) {
        val contentId = data.id
        val contentType = data.contentType
        moveToDetailFragment(contentId, contentType)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        applyRecyclerViewSpanCount(newConfig)
    }

}