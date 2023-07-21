package com.gun.presentation.ui.search.result

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gun.mvvm_cleanarchitecture.databinding.HolderSearchResultItemBinding
import com.gun.presentation.common.BasePagingAdapter
import com.gun.presentation.common.BaseViewHolder
import com.gun.presentation.common.ItemClickListener
import com.gun.domain.model.search.SearchResult
import com.gun.presentation.ui.favorite.FavoriteChangedListener

class SearchResultRecyclerAdapter(
    itemClickListener: ItemClickListener<SearchResult>? = null,
    val favoriteChangedListener: FavoriteChangedListener<SearchResult>
) : BasePagingAdapter<SearchResult, SearchResultRecyclerAdapter.ViewHolder>(itemClickListener)  {

    private var favoriteIdList: List<Int> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setFavoriteIdList(favoriteIdList: List<Int>) {
        this.favoriteIdList = favoriteIdList
        notifyDataSetChanged()
    }

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
        fun setData(data: SearchResult) {
            binding.data = data
            binding.isFavorite = favoriteIdList.contains(data.id)

            binding.root.setOnClickListener {
                itemClickListener?.onClickItem(data)
            }

            binding.checkBoxFavorite.setOnClickListener {
                favoriteChangedListener.onFavoriteChange(data, binding.checkBoxFavorite.isChecked)
            }
        }
    }
}
