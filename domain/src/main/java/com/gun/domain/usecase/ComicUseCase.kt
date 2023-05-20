package com.gun.domain.usecase

import com.gun.domain.model.Comic
import kotlinx.coroutines.flow.Flow

interface ComicUseCase {

    interface GetComicUseCase {
        operator fun invoke(offset: Int, limit: Int): Flow<Result<List<Comic>>>
    }

}