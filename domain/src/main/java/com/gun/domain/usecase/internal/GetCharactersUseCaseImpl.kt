package com.gun.domain.usecase.internal

import com.gun.domain.repository.CharacterRepository
import com.gun.domain.usecase.CharacterUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
import com.gun.domain.model.Character

@Singleton
class GetCharactersUseCaseImpl @Inject constructor(
    private val characterRepository: CharacterRepository
) : CharacterUseCase.GetCharacterUseCase {

    override fun invoke(page: Int, limit: Int): Flow<Result<List<Character>>> = flow {
        if (limit == 0) {
            emit(Result.failure(IllegalArgumentException()))
            return@flow
        }

        characterRepository.getCharacters(page, limit)
    }

}