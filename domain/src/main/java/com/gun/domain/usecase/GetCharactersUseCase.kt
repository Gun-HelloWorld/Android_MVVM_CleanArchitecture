package com.gun.domain.usecase

import com.gun.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    fun invoke(page: Int, limit: Int): Flow<Result<List<Character>>> = flow {
        if (limit == 0) {
            emit(Result.failure(IllegalArgumentException()))
            return@flow
        }

        characterRepository.getCharacters(page, limit)
    }
}