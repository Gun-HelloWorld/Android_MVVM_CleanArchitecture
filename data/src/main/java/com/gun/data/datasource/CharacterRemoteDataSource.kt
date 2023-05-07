package com.gun.data.datasource

import com.gun.data.entity.character.CharactersDto
import com.gun.data.network.MarvelApi

class CharacterRemoteDataSource(private val marvelApi: MarvelApi) : CharacterDataSource.Remote {
    override suspend fun getCharacters(page: Int, limit: Int): Result<CharactersDto> = try {
        val result = marvelApi.getCharacters(page, limit)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }
}