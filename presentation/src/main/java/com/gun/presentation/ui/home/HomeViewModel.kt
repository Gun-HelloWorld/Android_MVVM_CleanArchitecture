package com.gun.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gun.domain.usecase.GetHomeDataUseCase
import com.gun.presentation.ui.home.model.mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

const val HOME_LIST_PAGE = 0
const val HOME_LIST_LIMIT = 30

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeListDataUseCase: GetHomeDataUseCase
) : ViewModel() {
    private var loadingCount = AtomicInteger(0)

    private val _homeUiStateFlow: MutableStateFlow<HomeUiState> =
        MutableStateFlow(HomeUiState.ShowLoading(loadingCount.get()))
    val homeUiStateFlow = _homeUiStateFlow.asStateFlow()

    fun getHomeListData() {
        viewModelScope.launch(Dispatchers.IO) {
            getHomeListDataUseCase(HOME_LIST_PAGE, HOME_LIST_LIMIT)
                .onStart {
                    _homeUiStateFlow.emit(HomeUiState.ShowLoading(loadingCount.incrementAndGet()))
                }.onCompletion {
                    _homeUiStateFlow.emit(HomeUiState.ShowLoading(loadingCount.decrementAndGet()))
                }.catch {
                    // TODO 에러 메세지 수정
                    _homeUiStateFlow.emit(HomeUiState.ShowMessage("에러발생"))
                    it.printStackTrace()
                }.collectLatest { result ->
                    result.onSuccess { homeList ->
                        _homeUiStateFlow.emit(HomeUiState.ShowData(homeList.toUiModel()))
                    }.onFailure {
                        // TODO 에러 메세지 수정
                        _homeUiStateFlow.emit(HomeUiState.ShowMessage("에러발생"))
                    }
                }
        }
    }
}