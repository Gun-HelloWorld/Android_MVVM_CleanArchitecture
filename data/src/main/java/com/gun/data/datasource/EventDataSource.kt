package com.gun.data.datasource

import com.gun.data.entity.event.EventDto

class EventDataSource {

    interface Remote {
        suspend fun getEvent(eventId: Int): Result<EventDto>
        suspend fun getEventList(offset: Int, limit: Int): Result<EventDto>
    }

}