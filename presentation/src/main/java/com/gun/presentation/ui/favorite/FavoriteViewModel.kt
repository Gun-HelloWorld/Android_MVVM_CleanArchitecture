package com.gun.presentation.ui.favorite

import androidx.lifecycle.viewModelScope
import com.gun.domain.common.ContentType
import com.gun.domain.model.favorite.Favorite
import com.gun.domain.usecase.DeleteUseCase
import com.gun.domain.usecase.GetUseCase
import com.gun.domain.usecase.InsertUseCase
import com.gun.presentation.common.BaseViewModel
import com.gun.presentation.ui.common.ResultErrorType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteUseCase: GetUseCase.GetFavoriteUseCase,
    private val insertFavoriteUseCase: InsertUseCase.InsertFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteUseCase.DeleteFavoriteUseCase
): BaseViewModel() {

    fun getFavoriteList(contentType: ContentType?) {
        println("FavoriteViewModel - getFavoriteList()")
        viewModelScope.launch {
            getFavoriteUseCase(null)
                .onStart {
                    println("FavoriteViewModel - getFavoriteList() - onStart()")
                }.onCompletion {
                    println("FavoriteViewModel - getFavoriteList() - onCompletion()")
                }.catch {
                    println("FavoriteViewModel - getFavoriteList() - catch() - error : $ResultErrorType")
                    it.printStackTrace()
                }.collectLatest { result ->
                    println("FavoriteViewModel - getFavoriteList() - collectLatest(): $result")
                }
        }
    }

    fun insertFavorite(favorite: Favorite) {
        insertFavoriteUseCase(favorite)
    }

    fun deleteFavorite(favorite: Favorite) {
        deleteFavoriteUseCase(favorite)
    }

}