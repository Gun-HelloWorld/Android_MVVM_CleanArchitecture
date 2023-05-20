package com.gun.domain.usecase

import com.gun.domain.model.Creator
import kotlinx.coroutines.flow.Flow

interface CreatorUseCase {

    interface GetCreatorUseCase {
        operator fun invoke(offset: Int, limit: Int): Flow<Result<List<Creator>>>
    }

}