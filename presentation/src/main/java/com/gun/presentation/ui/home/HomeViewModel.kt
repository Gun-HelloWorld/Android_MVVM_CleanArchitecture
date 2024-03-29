package com.gun.presentation.ui.home

import androidx.lifecycle.viewModelScope
import com.gun.domain.common.EventType
import com.gun.domain.usecase.GetUseCase
import com.gun.presentation.common.BaseViewModel
import com.gun.presentation.ui.home.model.HomeListItem
import com.gun.presentation.ui.home.model.HomeUiModel
import com.gun.presentation.ui.home.model.mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

const val HOME_LIST_OFFSET = 0
const val HOME_LIST_LIMIT = 30
const val HOME_BANNER_COUNT = 5

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeListDataUseCase: GetUseCase.GetHomeDataUseCase,
) : BaseViewModel() {

    private val _homeUiDataStateFlow: MutableStateFlow<HomeUiModelState> =
        MutableStateFlow(HomeUiModelState.Nothing)
    val homeUiStateFlow = _homeUiDataStateFlow.asStateFlow()

    init {
        getHomeListData(HOME_LIST_OFFSET, HOME_LIST_LIMIT)
    }

    fun getHomeListData(offset: Int, limit: Int) {
        viewModelScope.launch {
            getHomeListDataUseCase(offset, limit)
                .onStart {
                    _loadingStateFlow.update { it.plus(1) }
                }.onCompletion {
                    _loadingStateFlow.update { it.minus(1) }
                }.catch {
                    _messageSharedFlow.emit(it.message ?: "Error")
                    it.printStackTrace()
                }.collectLatest { result ->
                    result.onSuccess { homeList ->
                        _homeUiDataStateFlow.value = HomeUiModelState.ShowData(homeList.toUiModel())
                    }.onFailure {
                        _messageSharedFlow.emit(it.message ?: "Error")
                    }
                }
        }
    }

    fun getFilterHomeBannerModel(homeUiModel: HomeUiModel): List<HomeListItem>? {
        return homeUiModel
            .fromUiModelType(EventType)
            .filterThumbnailAvailable()
            .sliceHomeListItem(HOME_BANNER_COUNT)
    }
}