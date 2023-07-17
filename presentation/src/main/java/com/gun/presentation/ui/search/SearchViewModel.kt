package com.gun.presentation.ui.search

import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.gun.domain.common.*
import com.gun.domain.usecase.GetDataUseCase.GetSearchDataUseCase
import com.gun.presentation.common.BaseViewModel
import com.gun.presentation.ui.common.PagingLoadStateListener
import com.gun.presentation.ui.common.ResultEmptyType
import com.gun.presentation.ui.common.ResultErrorType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchDataUseCase: GetSearchDataUseCase,
) : BaseViewModel() {
    private var latestSearchedQueryStateFlow: MutableStateFlow<String?> = MutableStateFlow(null)
    private var latestSearchedContentType: MutableStateFlow<ContentType> = MutableStateFlow(SeriesType)

    var queryStateFlow: MutableStateFlow<String?> = MutableStateFlow(null)
    var currentContentType: MutableStateFlow<ContentType> = MutableStateFlow(SeriesType)

    private val _searchUiDataStateFlow: MutableStateFlow<SearchUiModel> =
        MutableStateFlow(SearchUiModel.Initialize)
    val searchUiDataStateFlow = _searchUiDataStateFlow.asStateFlow()

    private val _searchUiEventSharedFlow = MutableSharedFlow<SearchUiEvent>()
    val searchUiEventSharedFlow = _searchUiEventSharedFlow.asSharedFlow()

    private val _searchPageMoveEventSharedFlow = MutableSharedFlow<SearchPageMoveEvent>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val searchPageMoveEventSharedFlow = _searchPageMoveEventSharedFlow.asSharedFlow()

    private fun getPageConfig() = PagingConfig(pageSize = 1)

    fun getContentTypeFromTabTitle(tabTitle: String) = when (tabTitle) {
        "Series" -> SeriesType
        "Comic" -> ComicType
        "Event" -> EventType
        "Character" -> CharacterType
        "Creator" -> CreatorType
        else -> null
    }

    fun setSearchUiEventSharedFlow(searchPageMoveEvent: SearchPageMoveEvent) {
        _searchPageMoveEventSharedFlow.tryEmit(searchPageMoveEvent)
    }

    val pagingLoadStateListener = object: PagingLoadStateListener {
        override fun onLoad(loadState: CombinedLoadStates, adapterItemCount: Int) {
            val isLoading = loadState.refresh is LoadState.Loading
            val isError = loadState.refresh is LoadState.Error
            val isEmpty = (loadState.refresh is LoadState.NotLoading) && adapterItemCount <= 0
            val isBadResult = isError || isEmpty

            viewModelScope.launch {
                if (isLoading) {
                    _searchUiEventSharedFlow.emit(SearchUiEvent.ShowLoading)
                } else {
                    _searchUiEventSharedFlow.emit(SearchUiEvent.HideLoading)
                }

                if (isBadResult) {
                    val badResultType = if (isError) ResultErrorType else ResultEmptyType
                    _searchUiEventSharedFlow.emit(SearchUiEvent.ShowBadResult(badResultType))
                }else {
                    _searchUiEventSharedFlow.emit(SearchUiEvent.HideBadResult)
                }
            }
        }
    }

    fun getSearchPagingData() {
        val query = queryStateFlow.value
        val contentType = currentContentType.value

        if (!isSearchAvailable(query, contentType)) return

        _searchUiDataStateFlow.value = SearchUiModel.Clear
        latestSearchedQueryStateFlow.value = query
        latestSearchedContentType.value = contentType

        val param = GetSearchDataUseCase.GetSearchParams(query!!, getPageConfig(), contentType)
        viewModelScope.launch {
            getSearchDataUseCase(param).cachedIn(this)
                .onStart {
                    _searchUiEventSharedFlow.emit(SearchUiEvent.ShowLoading)
                }.onCompletion {
                    _searchUiEventSharedFlow.emit(SearchUiEvent.HideLoading)
                }.catch {
                    _searchUiEventSharedFlow.emit(SearchUiEvent.ShowBadResult(ResultErrorType))
                    it.printStackTrace()
                }.collectLatest { result ->
                    _searchUiDataStateFlow.value = SearchUiModel.ShowData(contentType, result)
                }
        }
    }

    private fun isSearchAvailable(query: String?, contentType: ContentType): Boolean {
        if (query.isNullOrEmpty()) {
            return false
        }

        if (latestSearchedQueryStateFlow.value.equals(query) && latestSearchedContentType.value == contentType) {
            return false
        }

        return true
    }
}