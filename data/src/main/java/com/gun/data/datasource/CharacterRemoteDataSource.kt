package com.gun.data.datasource

import com.gun.data.entity.character.CharactersDto
import com.gun.data.network.MarvelApi

class CharacterRemoteDataSource(private val marvelApi: MarvelApi) : CharacterDataSource.Remote {

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

}