package com.gun.domain.usecase

import com.gun.domain.model.Event
import com.gun.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetEventUseCase @Inject constructor(
    private val eventRepository: EventRepository
) {
    fun invoke(page: Int, limit: Int): Flow<Result<List<Event>>> = flow {
        if (limit == 0) {
            emit(Result.failure(IllegalArgumentException()))
            return@flow
        }

        eventRepository.getEvents(page, limit)
    }
}