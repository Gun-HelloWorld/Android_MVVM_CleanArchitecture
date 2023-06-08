package com.gun.presentation.ui.search.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import com.gun.mvvm_cleanarchitecture.databinding.FragmentSearchResultBinding
import com.gun.presentation.common.ItemClickListener
import kotlinx.coroutines.launch

private const val KEY_SEARCH_RESULT_DATA = "key_search_result_data"

class SearchResultFragment : Fragment(), ItemClickListener<String> {
    private lateinit var binding: FragmentSearchResultBinding

    private lateinit var searchRecyclerAdapter: SearchResultRecyclerAdapter

    private lateinit var data: String

    companion object {
        fun newInstance(data: String): SearchResultFragment {
            val bundle = Bundle()
            bundle.putString(KEY_SEARCH_RESULT_DATA, data)

            val fragment = SearchResultFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        data = requireArguments().getString(KEY_SEARCH_RESULT_DATA)!!

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchRecyclerAdapter = SearchResultRecyclerAdapter(this)

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            data = this@SearchResultFragment.data
            recyclerView.adapter = searchRecyclerAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchRecyclerAdapter.submitData(
                    PagingData.from(
                        mutableListOf("$data Test1","$data Test2","$data Test3","$data Test4","$data Test5",)
                    )
                )
            }
        }
    }

    override fun onClickItem(data: String) {
//        TODO("Not yet implemented")
    }
}