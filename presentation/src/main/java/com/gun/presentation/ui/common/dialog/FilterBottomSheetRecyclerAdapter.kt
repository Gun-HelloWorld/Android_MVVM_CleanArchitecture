package com.gun.presentation.ui.common.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gun.mvvm_cleanarchitecture.databinding.HolderBottomSheetListItemBinding
import com.gun.presentation.common.BaseListAdapter
import com.gun.presentation.common.BaseViewHolder
import com.gun.presentation.common.ItemClickListener
import com.gun.presentation.ui.favorite.model.FilterItem

class FilterBottomSheetRecyclerAdapter(
    itemClickListener: ItemClickListener<FilterItem>? = null,
) : BaseListAdapter<FilterItem, FilterBottomSheetRecyclerAdapter.ViewHolder>(itemClickListener) {

    private lateinit var selectedItem: FilterItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = HolderBottomSheetListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(holder.bindingAdapterPosition)
        if (data.isSelected) selectedItem = data

        holder.setData(data)
    }

    inner class ViewHolder(
        val binding: HolderBottomSheetListItemBinding
    ) : BaseViewHolder(binding.root), ItemClickListener<FilterItem> {

        fun setData(data: FilterItem) {
            binding.data = data
            binding.onClickListener = this
        }

        override fun onClickItem(data: FilterItem) {
            val oldSelectedIndex = currentList.indexOf(selectedItem)
            val currentSelectedIndex = bindingAdapterPosition

            currentList[oldSelectedIndex].isSelected = false
            currentList[currentSelectedIndex].isSelected = true

            notifyItemChanged(oldSelectedIndex)
            notifyItemChanged(currentSelectedIndex)

            itemClickListener?.onClickItem(data)
        }
    }
}