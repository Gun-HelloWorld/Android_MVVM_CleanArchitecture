package com.gun.domain.repository

import com.gun.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun getEvents(page: Int, limit: Int): Flow<Result<List<Event>>>
}