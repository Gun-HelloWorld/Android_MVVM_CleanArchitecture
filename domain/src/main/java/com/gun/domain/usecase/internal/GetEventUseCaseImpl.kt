package com.gun.domain.usecase.internal

import com.gun.domain.model.Event
import com.gun.domain.repository.EventRepository
import com.gun.domain.usecase.EventUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetEventUseCaseImpl @Inject constructor(
    private val eventRepository: EventRepository
) : EventUseCase.GetEventUseCase {

    override fun invoke(page: Int, limit: Int): Flow<Result<List<Event>>> = flow {
        if (limit == 0) {
            emit(Result.failure(IllegalArgumentException()))
            return@flow
        }

        eventRepository.getEvents(page, limit)
    }

}