package com.gun.presentation.ui.home.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gun.domain.common.*
import com.gun.mvvm_cleanarchitecture.R
import com.gun.mvvm_cleanarchitecture.databinding.HolderHomeListBinding
import com.gun.presentation.common.BaseListAdapter
import com.gun.presentation.common.BaseViewHolder
import com.gun.presentation.ui.home.ItemClickListener
import com.gun.presentation.ui.home.model.*

class HomeMainRecyclerAdapter(
    private val context: Context,
    private val homeUiModel: HomeUiModel,
    private val itemClickListener: ItemClickListener
) : BaseListAdapter<HomeListItem, HomeMainRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = HolderHomeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = homeUiModel.homeUiSubModelList[holder.bindingAdapterPosition]
        holder.setData(data)
    }

    override fun getItemCount(): Int {
        return homeUiModel.homeUiSubModelList.size
    }

    inner class ViewHolder(val binding: HolderHomeListBinding) : BaseViewHolder(binding.root) {
        fun setData(data: HomeUiSubModel) {
            with(binding) {
                tvName.text = getNameFromUiModelType(data.contentType)
                val homeSubRecyclerAdapter = HomeSubRecyclerAdapter(itemClickListener)
                binding.recyclerView.adapter = homeSubRecyclerAdapter

                homeSubRecyclerAdapter.submitList(data.homeListItem)
            }
        }
    }

    private fun getNameFromUiModelType(contentType: ContentType) = with(context) {
        when(contentType) {
            is CharacterType -> getString(R.string.label_character)
            is ComicType -> getString(R.string.label_comic)
            is CreatorType -> getString(R.string.label_creator)
            is EventType -> getString(R.string.label_event)
            is SeriesType -> getString(R.string.label_series)
        }
    }
}