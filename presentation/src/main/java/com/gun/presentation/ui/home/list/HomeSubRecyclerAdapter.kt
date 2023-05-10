package com.gun.presentation.ui.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.gun.mvvm_cleanarchitecture.databinding.HolderHomeListItemBinding
import com.gun.presentation.common.BaseListAdapter
import com.gun.presentation.common.BaseViewHolder
import com.gun.presentation.ui.home.model.HomeListItem

class HomeSubRecyclerAdapter :
    BaseListAdapter<HomeListItem, HomeSubRecyclerAdapter.ViewHolder>() {

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
            with(binding) {
                tvName.text = data.name
                Glide.with(ivThumbnail).load(data.getListItemThumbnailUrl()).into(ivThumbnail)
            }

            itemView.setOnClickListener {
                Snackbar.make(it, "준비중", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}