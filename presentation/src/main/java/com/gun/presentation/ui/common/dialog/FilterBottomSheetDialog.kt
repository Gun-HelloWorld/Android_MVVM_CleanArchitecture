package com.gun.presentation.ui.common.dialog

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import com.gun.mvvm_cleanarchitecture.databinding.DialogBottomSheetItemSelectBinding
import com.gun.presentation.common.BaseBottomSheetDialog
import com.gun.presentation.common.ItemClickListener
import com.gun.presentation.ui.favorite.model.FilterItem

class FilterBottomSheetDialog(
    val activity: Activity,
) : BaseBottomSheetDialog<FilterItem>(activity), ItemClickListener<FilterItem> {

    private lateinit var binding: DialogBottomSheetItemSelectBinding

    private var listener: ItemClickListener<FilterItem>? = null

    private val filterBottomSheetRecyclerAdapter = FilterBottomSheetRecyclerAdapter(this)

    fun setItemClickListener(listener: ItemClickListener<FilterItem>): FilterBottomSheetDialog {
        this.listener = listener
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
        binding = DialogBottomSheetItemSelectBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        binding.recyclerView.adapter = filterBottomSheetRecyclerAdapter
    }

    override fun submitData(dataList: List<FilterItem>): FilterBottomSheetDialog {
        filterBottomSheetRecyclerAdapter.submitList(dataList)
        return this
    }

    override fun onClickItem(data: FilterItem) {
        listener?.onClickItem(data)
        dismiss()
    }
}