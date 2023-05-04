package com.gun.android_marvel_example.ui.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.gun.android_marvel_example.common.BaseListAdapter
import com.gun.android_marvel_example.common.BaseViewHolder
import com.gun.android_marvel_example.data.TestData
import com.gun.android_marvel_example.databinding.HolderHomeListBinding

class HomeListRecyclerAdapter :
    BaseListAdapter<TestData, HomeListRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = HolderHomeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = currentList[holder.adapterPosition]
        holder.setData(data)
    }

    inner class ViewHolder(val view: HolderHomeListBinding) :
        BaseViewHolder(view.root) {
        fun setData(data: TestData) {
            view.tvText.text = data.title
        }
    }
}