package com.gun.presentation.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.concurrent.atomic.AtomicInteger

abstract class BaseViewModel : ViewModel() {
    protected var loadingCount = AtomicInteger(0)

    protected val _messageSharedFlow = MutableSharedFlow<String>()
    val messageSharedFlow = _messageSharedFlow.asSharedFlow()

    protected val _loadingStateFlow = MutableStateFlow(loadingCount.get())
    val loadingStateFlow = _loadingStateFlow.asStateFlow()
}