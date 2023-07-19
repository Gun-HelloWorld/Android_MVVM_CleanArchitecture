package com.gun.data.datasource.remote

import com.gun.data.entity.response.remote.character.CharactersDto
import com.gun.data.entity.response.remote.comic.ComicDto
import com.gun.data.entity.response.remote.creator.CreatorDto
import com.gun.data.entity.response.remote.event.EventDto
import com.gun.data.entity.response.remote.series.SeriesDto

interface MarvelRemoteDataSource {

    suspend fun getCharacter(characterId: Int): Result<CharactersDto>

    suspend fun getCharacterList(offset: Int, limit: Int): Result<CharactersDto>

    suspend fun getComic(comicId: Int): Result<ComicDto>

    suspend fun getComicList(offset: Int, limit: Int): Result<ComicDto>

    suspend fun getCreator(creatorId: Int): Result<CreatorDto>

    suspend fun getCreatorList(offset: Int, limit: Int): Result<CreatorDto>

    suspend fun getEvent(eventId: Int): Result<EventDto>

    suspend fun getEventList(offset: Int, limit: Int): Result<EventDto>

    suspend fun getSeries(seriesId: Int): Result<SeriesDto>

    suspend fun getSeriesList(offset: Int, limit: Int): Result<SeriesDto>
}