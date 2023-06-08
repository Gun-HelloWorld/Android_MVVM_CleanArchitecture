package com.gun.domain.repository

import com.gun.domain.model.*
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {

    fun getCharacter(characterId: Int): Flow<Result<List<Character>>>

    fun getCharacterList(offset: Int, limit: Int): Flow<Result<List<Character>>>

    fun getComic(comicId: Int): Flow<Result<List<Comic>>>

    fun getComicList(offset: Int, limit: Int): Flow<Result<List<Comic>>>

    fun getCreator(creatorId: Int): Flow<Result<List<Creator>>>

    fun getCreatorList(offset: Int, limit: Int): Flow<Result<List<Creator>>>

    fun getEvent(eventId: Int): Flow<Result<List<Event>>>

    fun getEventList(offset: Int, limit: Int): Flow<Result<List<Event>>>

    fun getSeries(seriesId: Int): Flow<Result<List<Series>>>

    fun getSeriesList(offset: Int, limit: Int): Flow<Result<List<Series>>>

}