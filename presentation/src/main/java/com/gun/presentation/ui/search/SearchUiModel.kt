package com.gun.presentation.ui.search

import androidx.paging.PagingData
import com.gun.domain.common.ContentType
import com.gun.domain.model.search.SearchResult

sealed class SearchUiModel {

    object Initialize : SearchUiModel()

    object Clear : SearchUiModel()

    data class ShowData(val contentType: ContentType, val data: PagingData<SearchResult>) : SearchUiModel()

}