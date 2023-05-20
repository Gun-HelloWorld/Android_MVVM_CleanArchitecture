package com.gun.domain.usecase

import kotlinx.coroutines.flow.Flow
import com.gun.domain.model.Character

interface CharacterUseCase {

    interface GetCharacterUseCase {
        operator fun invoke(offset: Int, limit: Int): Flow<Result<List<Character>>>
    }

}