package com.gun.presentation.ui.search.result

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gun.mvvm_cleanarchitecture.databinding.HolderSearchResultItemBinding
import com.gun.presentation.common.BasePagingAdapter
import com.gun.presentation.common.BaseViewHolder
import com.gun.presentation.common.ItemClickListener

class SearchResultRecyclerAdapter(
    itemClickListener: ItemClickListener<String>? = null
) : BasePagingAdapter<String, SearchResultRecyclerAdapter.ViewHolder>(itemClickListener)  {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchResultRecyclerAdapter.ViewHolder {
        val binding = HolderSearchResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(holder.bindingAdapterPosition)?.let {
            holder.setData(it)
        }
    }

    inner class ViewHolder(val binding: HolderSearchResultItemBinding) : BaseViewHolder(binding.root) {
        fun setData(data: String) {

            binding.root.setOnClickListener {
                itemClickListener?.onClickItem(data)
            }
        }
    }
}
