package com.gun.presentation.ui.home

import androidx.lifecycle.viewModelScope
import com.gun.domain.usecase.GetHomeDataUseCase
import com.gun.presentation.common.BaseViewModel
import com.gun.presentation.ui.home.model.mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

const val HOME_LIST_PAGE = 0
const val HOME_LIST_LIMIT = 30

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeListDataUseCase: GetHomeDataUseCase
) : BaseViewModel() {

    init {
        getHomeListData()
    }

    private val _homeUiDataStateFlow: MutableStateFlow<HomeUiModelState> =
        MutableStateFlow(HomeUiModelState.Nothing)
    val homeUiStateFlow = _homeUiDataStateFlow.asStateFlow()

    fun getHomeListData() {
        viewModelScope.launch {
            getHomeListDataUseCase(HOME_LIST_PAGE, HOME_LIST_LIMIT)
                .onStart {
                    _loadingStateFlow.emit(loadingCount.incrementAndGet())
                }.onCompletion {
                    _loadingStateFlow.emit(loadingCount.decrementAndGet())
                }.catch {
                    _messageSharedFlow.emit(it.message ?: "Error")
                    it.printStackTrace()
                }.collectLatest { result ->
                    result.onSuccess { homeList ->
                        _homeUiDataStateFlow.emit(HomeUiModelState.ShowData(homeList.toUiModel()))
                    }.onFailure {
                        _messageSharedFlow.emit(it.message ?: "Error")
                    }
                }
        }
    }
}