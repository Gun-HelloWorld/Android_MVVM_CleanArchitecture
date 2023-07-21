package com.gun.presentation.ui.detail

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.gun.mvvm_cleanarchitecture.R
import com.gun.mvvm_cleanarchitecture.databinding.FragmentDetailBinding
import com.gun.presentation.common.BaseFragment
import com.gun.presentation.common.ImageLoadListener
import com.gun.presentation.common.util.BlurUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailFragment : BaseFragment(), ImageLoadListener, OnClickListener {

    private lateinit var binding: FragmentDetailBinding

    private val detailViewModel: DetailViewModel by viewModels()

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.imageLoadListener = this
        binding.checkBoxFavorite.setOnClickListener(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWindowLayoutNoLimit(true)
        initObserver()

        if (detailViewModel.detailUiStateFlow.value is DetailUiModelState.Nothing) {
            detailViewModel.getDetailData(args.contentId, args.contentType)
            detailViewModel.getFavoriteList(args.contentType)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        setWindowLayoutNoLimit(false)
    }

    private fun setMotionLayoutTransition(isLoading: Boolean) {
        val beginId = if (isLoading) R.id.motion_start_loading else R.id.motion_start
        val endId = R.id.motion_end
        binding.motionLayout.setTransition(beginId, endId)
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    detailViewModel.detailUiStateFlow.collect {
                        when (it) {
                            is DetailUiModelState.Nothing -> {}
                            is DetailUiModelState.ShowData -> {
                                binding.data = it.data
                                binding.viewDetailItem.setDetailUiModel(it.data)
                                setFadeAnimation(binding.root, *binding.groupDetailContents.referencedIds)
                            }
                        }
                    }
                }

                launch {
                    detailViewModel.loadingStateFlow.collect {
                        if (it >= 1) {
                            setMotionLayoutTransition(true)
                            if (!binding.shimmerViewContainer.isShimmerStarted) {
                                binding.shimmerViewContainer.startShimmer()
                            }
                        } else {
                            setMotionLayoutTransition(false)
                            binding.shimmerViewContainer.stopShimmer()
                        }
                    }
                }

                launch {
                    detailViewModel.messageSharedFlow.collect {
                        Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                    }
                }

                launch {
                    detailViewModel.favoriteIdListStateFlow.collect {
                        binding.isFavorite = it.contains(args.contentId)
                    }
                }
            }
        }
    }

    override fun onImageLoadedWithBitmap(imageView: ImageView, bitmap: Bitmap) {
        BlurUtil.blurToDetailBackground(requireContext(), imageView, bitmap, 60)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.check_box_favorite -> {
                if (v is CheckBox) {
                    detailViewModel.changeFavoriteStatus(v.isChecked, args.contentType)
                }
            }
        }
    }
}