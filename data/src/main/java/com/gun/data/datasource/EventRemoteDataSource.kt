package com.gun.data.datasource

import com.gun.data.entity.event.EventDto
import com.gun.data.network.MarvelApi

class EventRemoteDataSource(private val marvelApi: MarvelApi) : EventDataSource.Remote {

    override suspend fun getEvent(eventId: Int): Result<EventDto> = try {
        val result = marvelApi.getEvent(eventId)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getEventList(offset: Int, limit: Int): Result<EventDto> = try {
        val result = marvelApi.getEventList(offset, limit)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }

}