package com.gun.domain.usecase

import com.gun.domain.common.*
import com.gun.domain.model.detail.ContentDetail
import com.gun.domain.model.mapper.toContentDetail
import com.gun.domain.repository.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDetailDataUseCaseImpl @Inject constructor(
    private val marvelRepository: MarvelRepository
) : GetDataUseCase.GetDetailDataUseCase {

    override fun invoke(contentId: Int, contentType: ContentType): Flow<Result<ContentDetail>> =
        flow {

            // For ShimmerEffect Showing
            delay(500)

            if (0 >= contentId) {
                emit(Result.failure(IllegalArgumentException()))
                return@flow
            }

            val result: Result<ContentDetail>

            when (contentType) {
                is CharacterType -> {
                    result = marvelRepository.getCharacter(contentId).single()
                        .map { it.first().toContentDetail() }
                }
                is ComicType -> {
                    result = marvelRepository.getComic(contentId).single()
                        .map { it.first().toContentDetail() }
                }
                is SeriesType -> {
                    result = marvelRepository.getSeries(contentId).single()
                        .map { it.first().toContentDetail() }
                }
                is EventType -> {
                    result = marvelRepository.getEvent(contentId).single()
                        .map { it.first().toContentDetail() }
                }
                is CreatorType -> {
                    result = marvelRepository.getCreator(contentId).single()
                        .map { it.first().toContentDetail() }
                }
            }

            emit(result)
        }

}