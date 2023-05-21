package com.gun.presentation.ui.detail

import androidx.lifecycle.viewModelScope
import com.gun.domain.common.ContentType
import com.gun.domain.usecase.*
import com.gun.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailDataUseCase: GetDataUseCase.GetDetailDataUseCase
) : BaseViewModel() {

    private val _detailUiDataStateFlow: MutableStateFlow<DetailUiModelState> =
        MutableStateFlow(DetailUiModelState.Nothing)
    val detailUiStateFlow = _detailUiDataStateFlow.asStateFlow()

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
}