package com.gun.presentation.ui.detail

import androidx.lifecycle.viewModelScope
import com.gun.domain.common.ContentType
import com.gun.domain.model.favorite.Favorite
import com.gun.domain.model.mapper.parseFavorite
import com.gun.domain.usecase.DeleteUseCase
import com.gun.domain.usecase.GetUseCase
import com.gun.domain.usecase.InsertUseCase
import com.gun.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailDataUseCase: GetUseCase.GetDetailDataUseCase,
    private val getFavoriteUseCase: GetUseCase.GetFavoriteUseCase,
    private val insertFavoriteUseCase: InsertUseCase.InsertFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteUseCase.DeleteFavoriteUseCase
) : BaseViewModel() {

    private val _detailUiDataStateFlow: MutableStateFlow<DetailUiModelState> =
        MutableStateFlow(DetailUiModelState.Nothing)
    val detailUiStateFlow = _detailUiDataStateFlow.asStateFlow()

    private val _favoriteIdListStateFlow: MutableStateFlow<List<Int>> = MutableStateFlow(mutableListOf())
    val favoriteIdListStateFlow = _favoriteIdListStateFlow.asStateFlow()

    fun getDetailData(contentId: Int, contentType: ContentType) {
        viewModelScope.launch {
            getDetailDataUseCase(contentId,contentType )
                .onStart {
                    _loadingStateFlow.update { it.plus(1) }
                }.onCompletion {
                    _loadingStateFlow.update { it.minus(1) }
                }.catch {
                    _messageSharedFlow.emit(it.message ?: "Error")
                    it.printStackTrace()
                }.collectLatest { result ->
                    result.onSuccess { data ->
                        _detailUiDataStateFlow.value = DetailUiModelState.ShowData(data)
                    }.onFailure {
                        _messageSharedFlow.emit(it.message ?: "Error")
                    }
                }
        }
    }

    fun getFavoriteList(contentType: ContentType) {
        viewModelScope.launch {
            getFavoriteUseCase(contentType)
                .onStart {
                    _loadingStateFlow.update { it.plus(1) }
                }.onCompletion {
                    _loadingStateFlow.update { it.minus(1) }
                }.catch {
                    _messageSharedFlow.emit(it.message ?: "Error")
                    it.printStackTrace()
                }.collectLatest { result ->
                    result.onSuccess { data ->
                        val favoriteIdList = data.map { it.id }.toMutableList()
                        _favoriteIdListStateFlow.value = favoriteIdList
                    }
                }
        }
    }

    fun changeFavoriteStatus(isChecked: Boolean, contentType: ContentType) {
        val contentDetail =  (_detailUiDataStateFlow.value as DetailUiModelState.ShowData).data
        val favorite = contentDetail.parseFavorite(contentType)

        if (isChecked) {
            insertFavorite(favorite)
        } else {
            deleteFavorite(favorite)
        }
    }

    private fun insertFavorite(favorite: Favorite) {
        viewModelScope.launch {
            insertFavoriteUseCase(favorite)
                .collectLatest { result ->
                    result.onSuccess { insertFavorite ->
                        val favoriteIdList = _favoriteIdListStateFlow.value.toMutableList()
                        favoriteIdList.add(insertFavorite.id)
                        _favoriteIdListStateFlow.value = favoriteIdList
                    }.onFailure {
                        _messageSharedFlow.emit(it.message ?: "Error")
                    }
                }
        }
    }

    private fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch {
            deleteFavoriteUseCase(favorite)
                .collectLatest { result ->
                    result.onSuccess { deletedFavorite ->
                        val favoriteIdList = _favoriteIdListStateFlow.value.toMutableList()
                        favoriteIdList.remove(deletedFavorite.id)
                        _favoriteIdListStateFlow.value = favoriteIdList
                    }.onFailure {
                        _messageSharedFlow.emit(it.message ?: "Error")
                    }
                }
        }
    }
}