package com.gun.presentation.ui.favorite.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gun.domain.model.favorite.Favorite
import com.gun.mvvm_cleanarchitecture.databinding.HolderFavoriteListItemBinding
import com.gun.presentation.common.BaseListAdapter
import com.gun.presentation.common.BaseViewHolder
import com.gun.presentation.common.ItemClickListener

class FavoriteRecyclerAdapter(
    itemClickListener: ItemClickListener<Favorite>? = null
) : BaseListAdapter<Favorite, FavoriteRecyclerAdapter.ViewHolder>(itemClickListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = HolderFavoriteListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(holder.bindingAdapterPosition)
        holder.setData(data)
    }

    inner class ViewHolder(val binding: HolderFavoriteListItemBinding) : BaseViewHolder(binding.root) {
        fun setData(data: Favorite) {
            binding.data = data
            binding.onClickListener = itemClickListener
        }
    }
}