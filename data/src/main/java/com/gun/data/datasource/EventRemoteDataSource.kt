package com.gun.data.datasource

import com.gun.data.entity.event.EventDto
import com.gun.data.network.MarvelApi

class EventRemoteDataSource(private val marvelApi: MarvelApi) : EventDataSource.Remote {
    override suspend fun getEvents(page: Int, limit: Int): Result<EventDto> = try {
        val result = marvelApi.getEvents(page, limit)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }
}