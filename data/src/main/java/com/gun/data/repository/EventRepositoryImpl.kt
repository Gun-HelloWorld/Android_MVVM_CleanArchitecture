package com.gun.data.repository

import com.gun.data.datasource.EventDataSource
import com.gun.data.mapper.toDomainModel
import com.gun.domain.model.Event
import com.gun.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EventRepositoryImpl(
    private val eventDataSource: EventDataSource.Remote
) : EventRepository {

    override fun getEvent(eventId: Int): Flow<Result<List<Event>>> = flow {
        emit(eventDataSource.getEvent(eventId).map { it.toDomainModel() })
    }

    override fun getEventList(offset: Int, limit: Int): Flow<Result<List<Event>>> = flow {
        emit(eventDataSource.getEventList(offset, limit).map { it.toDomainModel() })
    }

}