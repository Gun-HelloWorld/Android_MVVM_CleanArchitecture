package com.gun.data.repository

import com.gun.data.datasource.CharacterDataSource
import com.gun.data.mapper.toDomainModel
import com.gun.domain.model.Character
import com.gun.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterRepositoryImpl(
    private val characterDataSource: CharacterDataSource.Remote
) : CharacterRepository {
    override suspend fun getCharacters(page: Int, limit: Int): Flow<Result<List<Character>>> = flow {
        emit(characterDataSource.getCharacters(page, limit).map { it.toDomainModel() })
    }
}