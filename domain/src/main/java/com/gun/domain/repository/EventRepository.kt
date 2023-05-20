package com.gun.domain.repository

import com.gun.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun getEvent(eventId: Int): Flow<Result<List<Event>>>
    fun getEventList(offset: Int, limit: Int): Flow<Result<List<Event>>>
}