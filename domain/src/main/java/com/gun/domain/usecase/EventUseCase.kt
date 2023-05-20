package com.gun.domain.usecase

import com.gun.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface EventUseCase {

    interface GetEventUseCase {
        operator fun invoke(offset: Int, limit: Int): Flow<Result<List<Event>>>
    }

}