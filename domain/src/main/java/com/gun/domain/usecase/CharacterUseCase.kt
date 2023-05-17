package com.gun.domain.usecase

import kotlinx.coroutines.flow.Flow

interface CharacterUseCase {

    interface GetCharacterUseCase {
        operator fun invoke(page: Int, limit: Int): Flow<Result<List<Character>>>
    }

}