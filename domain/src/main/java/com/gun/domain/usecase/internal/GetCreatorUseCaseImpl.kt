package com.gun.domain.usecase.internal

import com.gun.domain.model.Creator
import com.gun.domain.repository.CreatorRepository
import com.gun.domain.usecase.CreatorUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCreatorUseCaseImpl @Inject constructor(
    private val creatorRepository: CreatorRepository
) : CreatorUseCase.GetCreatorUseCase {

    override fun invoke(page: Int, limit: Int): Flow<Result<List<Creator>>> = flow {
        if (limit == 0) {
            emit(Result.failure(IllegalArgumentException()))
            return@flow
        }

        creatorRepository.getCreators(page, limit)
    }

}