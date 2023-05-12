package com.gun.presentation.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel : ViewModel() {
    protected val _messageSharedFlow = MutableSharedFlow<String>()
    val messageSharedFlow = _messageSharedFlow.asSharedFlow()

    protected val _loadingStateFlow = MutableStateFlow(0)
    val loadingStateFlow = _loadingStateFlow.asStateFlow()
}