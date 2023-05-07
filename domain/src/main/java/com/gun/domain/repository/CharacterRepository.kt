package com.gun.domain.repository

import com.gun.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters(page: Int, limit: Int): Flow<Result<List<Character>>>
}