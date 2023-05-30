package com.gun.presentation.ui.detail

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.gun.domain.model.detail.ContentDetail
import com.gun.mvvm_cleanarchitecture.R
import com.gun.mvvm_cleanarchitecture.databinding.ViewDetailContentsBinding
import com.gun.mvvm_cleanarchitecture.databinding.ViewDetailContentsItemBinding

class DetailItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private lateinit var contentDetail: ContentDetail

    private lateinit var binding: ViewDetailContentsBinding

    init {
        if (!isInEditMode) {
            binding = ViewDetailContentsBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    fun setDetailUiModel(contentDetail: ContentDetail) {
        this.contentDetail = contentDetail
        init()
    }

    private fun init() {
        binding.data = contentDetail

        if (contentDetail.characterInfoList.isNotEmpty()) {
            val label = context.getString(R.string.label_characters)
            if (!isAlreadyAddedView(label)) {
                val value = contentDetail.joinStringCharacters()
                binding.layoutRoot.addView(generateSubjectView(label, value))
            }
        }

        if (contentDetail.comicInfoList.isNotEmpty()) {
            val label = context.getString(R.string.label_comics)
            if (!isAlreadyAddedView(label)) {
                val value = contentDetail.joinStringComics()
                binding.layoutRoot.addView(generateSubjectView(label, value))
            }
        }

        if (contentDetail.seriesInfoList.isNotEmpty()) {
            val label = context.getString(R.string.label_series)
            if (!isAlreadyAddedView(label)) {
                val value = contentDetail.joinStringSeries()
                binding.layoutRoot.addView(generateSubjectView(label, value))
            }
        }

        if (contentDetail.storyInfoList.isNotEmpty()) {
            val label = context.getString(R.string.label_stories)
            if (!isAlreadyAddedView(label)) {
                val value = contentDetail.joinStringStories()
                binding.layoutRoot.addView(generateSubjectView(label, value))
            }
        }

        if (contentDetail.eventInfoList.isNotEmpty()) {
            val label = context.getString(R.string.label_events)
            if (!isAlreadyAddedView(label)) {
                val value = contentDetail.joinStringEvent()
                binding.layoutRoot.addView(generateSubjectView(label, value))
            }
        }

        if (contentDetail.creatorInfoList.isNotEmpty()) {
            val label = context.getString(R.string.label_creators)
            if (!isAlreadyAddedView(label)) {
                val value = contentDetail.joinStringCreator()
                binding.layoutRoot.addView(generateSubjectView(label, value))
            }
        }
    }

    private fun isAlreadyAddedView(label: String): Boolean {
        return binding.root.findViewWithTag<View>(label) != null
    }

    private fun generateSubjectView(label: String, value: String): View {
        val itemView = ViewDetailContentsItemBinding.inflate(
            LayoutInflater.from(context),
            this,
            false
        )

        itemView.root.setTag(label)
        itemView.tvLabel.text = label
        itemView.tvValue.text = value

        return itemView.root
    }
}