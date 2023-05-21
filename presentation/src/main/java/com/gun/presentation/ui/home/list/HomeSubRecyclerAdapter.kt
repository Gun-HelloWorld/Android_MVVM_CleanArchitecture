package com.gun.presentation.ui.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gun.mvvm_cleanarchitecture.databinding.HolderHomeListItemBinding
import com.gun.presentation.common.BaseListAdapter
import com.gun.presentation.common.BaseViewHolder
import com.gun.presentation.ui.home.ItemClickListener
import com.gun.presentation.ui.home.model.HomeListItem

class HomeSubRecyclerAdapter(
    private val itemClickListener: ItemClickListener
) : BaseListAdapter<HomeListItem, HomeSubRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = HolderHomeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(holder.bindingAdapterPosition)
        holder.setData(data)
    }

    inner class ViewHolder(val binding: HolderHomeListItemBinding) : BaseViewHolder(binding.root) {
        fun setData(data: HomeListItem) {
            binding.data = data

            itemView.setOnClickListener {
                itemClickListener.onClickItem(data)
            }
        }
    }
}