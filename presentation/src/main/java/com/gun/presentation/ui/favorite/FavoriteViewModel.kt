package com.gun.presentation.ui.favorite

import androidx.lifecycle.viewModelScope
import com.gun.domain.common.ContentType
import com.gun.domain.usecase.DeleteUseCase
import com.gun.domain.usecase.GetUseCase
import com.gun.domain.usecase.InsertUseCase
import com.gun.presentation.common.BaseViewModel
import com.gun.presentation.ui.common.ResultErrorType
import com.gun.presentation.ui.favorite.model.FavoriteUiModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteUseCase: GetUseCase.GetFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteUseCase.DeleteFavoriteUseCase
): BaseViewModel() {

    private val _favoriteUiDataStateFlow: MutableStateFlow<FavoriteUiModelState> =
        MutableStateFlow(FavoriteUiModelState.Nothing)
    val favoriteUiStateFlow = _favoriteUiDataStateFlow.asStateFlow()

    fun getFavoriteList(contentType: ContentType?) {
        println("FavoriteViewModel - getFavoriteList()")
        viewModelScope.launch {
            getFavoriteUseCase(contentType)
                .onStart {
                    println("FavoriteViewModel - getFavoriteList() - onStart()")
                }.onCompletion {
                    println("FavoriteViewModel - getFavoriteList() - onCompletion()")
                }.catch {
                    println("FavoriteViewModel - getFavoriteList() - catch() - error : $ResultErrorType")
                    it.printStackTrace()
                }.collectLatest { result ->
                    println("FavoriteViewModel - getFavoriteList() - collectLatest(): $result")

                    result.onSuccess { favoriteList ->
                        _favoriteUiDataStateFlow.value = FavoriteUiModelState.ShowData(favoriteList)
                    }.onFailure {
                        _messageSharedFlow.emit(it.message ?: "Error")
                    }
                }
        }
    }

}