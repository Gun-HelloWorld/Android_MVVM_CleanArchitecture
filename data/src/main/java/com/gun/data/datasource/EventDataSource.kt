package com.gun.data.datasource

import com.gun.data.entity.event.EventDto

class EventDataSource {
    interface Remote {
        suspend fun getEvents(page: Int, limit: Int): Result<EventDto>
    }
}