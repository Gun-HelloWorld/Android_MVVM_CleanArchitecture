package com.gun.presentation.ui.home.banner

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.gun.presentation.ui.home.ItemClickListener
import com.gun.presentation.ui.home.model.HomeListItem


class HomeBannerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val itemClickListener: ItemClickListener
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragmentList: MutableList<Fragment> = mutableListOf()
    private val dataList: MutableList<HomeListItem> = mutableListOf()

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun onBindViewHolder(
        holder: FragmentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)

        holder.itemView.setOnClickListener {
            itemClickListener.onClickItem((dataList[holder.adapterPosition]))
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun replaceData(dataList: List<HomeListItem>) {
        val bannerFragmentList = dataList.map { HomeBannerFragment.newInstance(it) }
        this.fragmentList.clear()
        this.fragmentList.addAll(bannerFragmentList)

        this.dataList.clear()
        this.dataList.addAll(dataList)

        notifyDataSetChanged()
    }
}