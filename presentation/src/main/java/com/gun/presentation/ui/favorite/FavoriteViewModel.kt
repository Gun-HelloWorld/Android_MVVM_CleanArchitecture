package com.gun.presentation.ui.favorite

import androidx.lifecycle.viewModelScope
import com.gun.domain.common.ContentType
import com.gun.domain.usecase.GetUseCase
import com.gun.presentation.common.BaseViewModel
import com.gun.presentation.ui.common.ResultEmptyType
import com.gun.presentation.ui.common.ResultErrorType
import com.gun.presentation.ui.favorite.model.FavoriteUiEvent
import com.gun.presentation.ui.favorite.model.FavoriteUiFilterState
import com.gun.presentation.ui.favorite.model.FavoriteUiModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteUseCase: GetUseCase.GetFavoriteUseCase,
): BaseViewModel() {

    private val _favoriteUiDataStateFlow: MutableStateFlow<FavoriteUiModelState> =
        MutableStateFlow(FavoriteUiModelState.Nothing)
    val favoriteUiStateFlow = _favoriteUiDataStateFlow.asStateFlow()

    private val _favoriteUiEventSharedFlow = MutableSharedFlow<FavoriteUiEvent>()
    val favoriteUiEventSharedFlow = _favoriteUiEventSharedFlow.asSharedFlow()

    val selectedFavoriteFilter: MutableStateFlow<FavoriteUiFilterState> =
        MutableStateFlow(FavoriteUiFilterState.All)

    fun requestFavoriteList(contentType: ContentType?) {
        val isFirstLoad = _favoriteUiDataStateFlow.value is FavoriteUiModelState.Nothing
        val shimmerDuration = if (isFirstLoad) 1000L else 0L

        viewModelScope.launch {
            getFavoriteUseCase(contentType, shimmerDuration.toDuration(DurationUnit.MILLISECONDS))
                .onStart {
                    _favoriteUiEventSharedFlow.emit(FavoriteUiEvent.ShowLoading)
                }.onCompletion {
                    _favoriteUiEventSharedFlow.emit(FavoriteUiEvent.HideLoading)
                }.catch {
                    _favoriteUiEventSharedFlow.emit(FavoriteUiEvent.ShowBadResult(ResultErrorType))
                    it.printStackTrace()
                }.collectLatest { result ->
                    result.onSuccess { favoriteList ->
                        _favoriteUiDataStateFlow.value = FavoriteUiModelState.ShowData(favoriteList)

                        val badResult = if (favoriteList.isEmpty()) {
                            FavoriteUiEvent.ShowBadResult(ResultEmptyType)
                        } else {
                            FavoriteUiEvent.HideBadResult
                        }

                        _favoriteUiEventSharedFlow.emit(badResult)
                    }.onFailure {
                        _favoriteUiEventSharedFlow.emit(FavoriteUiEvent.ShowBadResult(ResultErrorType))
                    }
                }
        }
    }

}