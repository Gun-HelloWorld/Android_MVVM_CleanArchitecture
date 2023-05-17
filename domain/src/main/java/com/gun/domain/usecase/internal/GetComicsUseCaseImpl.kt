package com.gun.domain.usecase.internal

import com.gun.domain.model.Comic
import com.gun.domain.repository.ComicRepository
import com.gun.domain.usecase.ComicUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetComicsUseCaseImpl @Inject constructor(
    private val comicRepository: ComicRepository
) : ComicUseCase.GetComicUseCase {

    override fun invoke(page: Int, limit: Int): Flow<Result<List<Comic>>> = flow {
        if (limit == 0) {
            emit(Result.failure(IllegalArgumentException()))
            return@flow
        }

        comicRepository.getComics(page, limit)
    }

}