package com.gun.data.datasource

import com.gun.data.entity.character.CharactersDto
import com.gun.data.entity.comic.ComicDto
import com.gun.data.entity.creator.CreatorDto
import com.gun.data.entity.event.EventDto
import com.gun.data.entity.series.SeriesDto
import com.gun.data.network.MarvelApi

class MarvelRemoteDataSourceImpl(
    private val marvelApi: MarvelApi
) : MarvelRemoteDataSource {

    override suspend fun getCharacter(characterId: Int): Result<CharactersDto>  = try {
        val result = marvelApi.getCharacter(characterId)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getCharacterList(offset: Int, limit: Int): Result<CharactersDto> = try {
        val result = marvelApi.getCharacterList(offset, limit)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getComic(comicId: Int): Result<ComicDto> = try {
        val result = marvelApi.getComic(comicId)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getComicList(offset: Int, limit: Int): Result<ComicDto> = try {
        val result = marvelApi.getComicList(offset, limit)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getCreator(creatorId: Int): Result<CreatorDto> = try {
        val result = marvelApi.getCreator(creatorId)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getCreatorList(offset: Int, limit: Int): Result<CreatorDto> = try {
        val result = marvelApi.getCreatorList(offset, limit)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }

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

    override suspend fun getSeries(seriesId: Int): Result<SeriesDto> = try {
        val result = marvelApi.getSeries(seriesId)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getSeriesList(offset: Int, limit: Int): Result<SeriesDto> = try {
        val result = marvelApi.getSeriesList(offset, limit)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }

}