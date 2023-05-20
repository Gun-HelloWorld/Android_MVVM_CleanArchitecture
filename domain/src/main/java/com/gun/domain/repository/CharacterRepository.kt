package com.gun.domain.repository

import com.gun.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacter(characterId: Int): Flow<Result<List<Character>>>
    fun getCharacterList(offset: Int, limit: Int): Flow<Result<List<Character>>>
}